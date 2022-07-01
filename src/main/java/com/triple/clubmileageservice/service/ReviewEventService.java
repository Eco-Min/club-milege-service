package com.triple.clubmileageservice.service;

import com.triple.clubmileageservice.domain.PointCalculator;
import com.triple.clubmileageservice.domain.entity.*;
import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.dto.ReviewPointListDto;
import com.triple.clubmileageservice.repository.*;
import com.triple.clubmileageservice.vo.RequestEventVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewEventService {
    private final ReviewEntityRepository reviewEntityRepository;
    private final ReviewHISTEntityRepository reviewHISTEntityRepository;
    private final PhotoRepository photoRepository;
    private final PlaceEntityRepository placeEntityRepository;

    public void events(EventDto eventDto){
        switch (eventDto.getActionType()) {
            case ADD -> createReview(eventDto);
            case MOD -> modifiedReview(eventDto);
            case DELETE -> deleteReview(eventDto);
        }
    }

    @Transactional
    public void createReview(EventDto eventDto) {
        PlaceEntity placeEntity = placeEntityRepository.findById(eventDto.getPlaceId()).get();

        ReviewEntity review = new ReviewEntity(eventDto.getReviewId(), eventDto.getContent());
        review.savePlace(placeEntity);
        reviewEntityRepository.save(review);

        PointCalculator pointCalculator = new PointCalculator();
        int reviewPoint = pointCalculator.addContentAndPhotos(eventDto.getContent(), eventDto.getAttachedPhotoIds(), null);

        ReviewHISTEntity reviewHISTEntity = new ReviewHISTEntity(eventDto.getActionType());
        reviewHISTEntity.saveReview(review);
        reviewHISTEntity.setReviewPoint(reviewPoint);
        reviewHISTEntityRepository.save(reviewHISTEntity);
    }
    @Transactional
    public void modifiedReview(EventDto eventDto) {
        ReviewEntity review = reviewEntityRepository.findById(eventDto.getReviewId()).get();
        List<String> oldPhotos = review.getPhotos().stream().map(PhotoEntity::getId).toList();

        EventDto oldEventDto = EventDto.builder()
                .content(review.getContent())
                .attachedPhotoIds(oldPhotos)
                .build();

        PointCalculator pointCalculator = new PointCalculator();
        int reviewPoint = pointCalculator.modifiedContentAndPhotos(oldEventDto, eventDto);

        ReviewHISTEntity reviewHISTEntity = new ReviewHISTEntity(eventDto.getActionType());
        reviewHISTEntity.saveReview(review);
        reviewHISTEntity.setReviewPoint(reviewPoint);
        reviewHISTEntityRepository.save(reviewHISTEntity);
    }

    private void deleteReview(EventDto eventDto) {

    }

    public List<ReviewPointListDto> selectDurationPoint(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<ReviewPointListDto> search = reviewEntityRepository.search(userId, startDate, endDate);
        return search;
    }
}
