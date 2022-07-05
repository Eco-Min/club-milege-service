package com.triple.clubmileageservice.service;

import com.triple.clubmileageservice.domain.PointCalculator;
import com.triple.clubmileageservice.domain.entity.*;
import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.dto.ReviewPointListDto;
import com.triple.clubmileageservice.exception.PlaceException;
import com.triple.clubmileageservice.exception.ReviewException;
import com.triple.clubmileageservice.exception.UserException;
import com.triple.clubmileageservice.repository.query.ReviewEventRepository;
import com.triple.clubmileageservice.reqres.EventRes;
import com.triple.clubmileageservice.reqres.UserPointRes;
import com.triple.clubmileageservice.service.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ReviewEventService {
    private final UserService userService;
    private final PlaceService placeService;
    private final ReviewService reviewService;
    private final ReviewHistService reviewHistService;
    private final PhotoService photoService;
    private final PointCalculator pointCalculator;
    private final ReviewEventRepository reviewEventRepository;

    public EventRes events(EventDto eventDto) {
        switch (eventDto.getActionType()) {
            case ADD -> {
                return createReview(eventDto);
            }
            case MOD -> {
                return modifiedReview(eventDto);
            }
            case DELETE -> {
                return deleteReview(eventDto);
            }
            default -> {
                return null;
            }
        }
    }

    public EventRes createReview(EventDto eventDto) {
        log.info("createReview");
        UserEntity userEntity = checkUser(eventDto);
        PlaceEntity placeEntity = checkPlace(eventDto);

        ReviewEntity review = saveReviewEntity(eventDto, userEntity, placeEntity);
        DeleteAndSavePhotosIfPresent(true, eventDto, review);

        int getPoint = pointCalculator.addContentAndPhotos(eventDto.getContent(), eventDto.getAttachedPhotoIds());
        int placeBonbonsPoint = reviewEventRepository.checkPlaceBonbonsPoint(placeEntity.getPlaceNo());
        saveReviewHist(getPoint, placeBonbonsPoint, eventDto, review);

        return new EventRes(getPoint, placeBonbonsPoint, eventDto);
    }

    public EventRes modifiedReview(EventDto eventDto) {
        log.info("modifiedReview");
        checkUser(eventDto);
        PlaceEntity placeEntity = checkPlace(eventDto);
        ReviewEntity review = reviewService.findByReviewIdAndUseYN(eventDto.getReviewId());
        if (review == null) {
            throw new ReviewException(eventDto.getReviewId(), false);
        }

        List<String> oldPhotos = review.getPhotos().stream().map(PhotoEntity::getPhotoId).toList();
        EventDto oldEventDto = EventDto.builder()
                .content(review.getContent())
                .attachedPhotoIds(oldPhotos)
                .build();

        boolean photoDiff = photoDiff(eventDto, oldPhotos);
        DeleteAndSavePhotosIfPresent(photoDiff, eventDto, review);

        int getPoint = pointCalculator.modifiedContentAndPhotos(oldEventDto, eventDto);
        int hasBonusPoint = reviewEventRepository.checkPlaceBonbonsPoint(placeEntity.getPlaceNo());
        saveReviewHist(getPoint, hasBonusPoint, eventDto, review);
        review.changeContent(eventDto.getContent());

        return new EventRes(getPoint, hasBonusPoint, eventDto);
    }

    public EventRes deleteReview(EventDto eventDto) {
        log.info("deleteReview");
        checkUser(eventDto);
        checkPlace(eventDto);

        ReviewEntity getReview = reviewService.findByReviewId(eventDto.getReviewId());
        if (getReview == null || getReview.getUseYN().equals("N")) {
            throw new ReviewException(eventDto.getReviewId(), false);
        }
        getReview.deleteUse();
        photoService.deleteAllPhotoIds(eventDto.getAttachedPhotoIds());

        int hasPoint = reviewEventRepository.userHasPointInPlace(getReview.getReviewNo());
        int hasBonusPoint = reviewEventRepository.userHasBonusPointInPlace(getReview.getReviewNo());
        saveReviewHist(hasPoint, hasBonusPoint, eventDto, getReview);
        return new EventRes(hasPoint, hasBonusPoint, eventDto);
    }

    public List<ReviewPointListDto> selectDurationPoint(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        return reviewEventRepository.searchUserDurationPoint(userId, startDate, endDate);
    }

    public UserPointRes selectUserPoint(String userId) {
        Integer userPoint = reviewEventRepository.allPointFromUser(userId);
        return new UserPointRes(userId, userPoint);
    }


    private void saveReviewHist(int reviewPoint, int bonusPoint, EventDto eventDto, ReviewEntity review) {
        ReviewHistEntity reviewHistEntity = new ReviewHistEntity(eventDto.getActionType());
        reviewHistEntity.saveReview(review);
        reviewHistEntity.setReviewPoint(reviewPoint);
        reviewHistEntity.setBonusPoint(bonusPoint);
        reviewHistService.save(reviewHistEntity);
    }


    private void DeleteAndSavePhotosIfPresent(boolean hasPhotos, EventDto eventDto, ReviewEntity review) {
        if (hasPhotos) {
            List<String> photos = review.getPhotos().stream().map(PhotoEntity::getPhotoId).toList();
            photoService.deleteAllPhotoIds(photos);
        }
        if (eventDto.getAttachedPhotoIds().size()>0 && hasPhotos) {
            List<PhotoEntity> photos = new ArrayList<>();
            for (String attachedPhotoId : eventDto.getAttachedPhotoIds()) {
                PhotoEntity photo = new PhotoEntity(attachedPhotoId);
                photo.saveReview(review);
                photos.add(photo);
            }
            photoService.saveAll(photos);
        }
    }

    private ReviewEntity saveReviewEntity(EventDto eventDto, UserEntity userEntity, PlaceEntity placeEntity) {
        ReviewEntity reviewId = reviewService.findByReviewId(eventDto.getReviewId());
        if (reviewId != null) {
            throw new ReviewException(eventDto.getReviewId(), true);
        }
        ReviewEntity review = new ReviewEntity(eventDto.getReviewId(), eventDto.getContent());
        review.savePlace(placeEntity);
        review.saveUser(userEntity);
        reviewService.save(review);
        return review;
    }

    private UserEntity checkUser(EventDto eventDto) {
        UserEntity userEntity = userService.findUserId(eventDto.getUserId());
        if (userEntity == null) {
            throw new UserException(eventDto.getUserId());
        }
        return userEntity;
    }

    private PlaceEntity checkPlace(EventDto eventDto) {
        PlaceEntity placeEntity = placeService.findPlaceId(eventDto.getPlaceId());
        if (placeEntity == null) {
            throw new PlaceException(eventDto.getUserId());
        }
        return placeEntity;
    }
//    e4d1a64e-a531-46de-88d0-ff0ed70c0b15
    private boolean photoDiff(EventDto eventDto, List<String> oldPhotos) {
        if (eventDto.getAttachedPhotoIds().size() != oldPhotos.size()) {
            return true;
        }
        Set<String> photoSet = new HashSet<>();
        photoSet.addAll(oldPhotos);
        photoSet.addAll(eventDto.getAttachedPhotoIds());
        return photoSet.size() != oldPhotos.size();
    }
}
