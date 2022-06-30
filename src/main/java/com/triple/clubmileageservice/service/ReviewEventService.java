package com.triple.clubmileageservice.service;

import com.triple.clubmileageservice.domain.PointCalculator;
import com.triple.clubmileageservice.domain.entity.*;
import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.dto.ReviewPointListDto;
import com.triple.clubmileageservice.repository.*;
import com.triple.clubmileageservice.vo.EventVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewEventService {
    private final ReviewEntityRepository reviewEntityRepository;
    private final ReviewHISTEntityRepository reviewHISTEntityRepository;
    private final PhotoRepository photoRepository;
    private final PlaceEntityRepository placeEntityRepository;

    @Transactional
    public void events(EventDto eventDto){
        EventVo eventVo = EventVo.createEventVo(eventDto);
        switch (eventVo.getActionType()) {
            case ADD -> createReview(eventVo);
            case MOD -> modifiedReview(eventVo);
            case DELETE -> deleteReview(eventVo);
        }
    }

    private void createReview(EventVo eventVo) {
        PlaceEntity placeEntity = placeEntityRepository.findById(eventVo.getPlaceId()).get();

        ReviewEntity review = new ReviewEntity(eventVo.getReviewId(), eventVo.getContent());
        review.savePlace(placeEntity);
        reviewEntityRepository.save(review);

        PointCalculator pointCalculator = new PointCalculator();
        int reviewPoint = pointCalculator.addContentAndPhotos(eventVo.getContent(), eventVo.getAttachedPhotoIds(), null);

        ReviewHISTEntity reviewHISTEntity = new ReviewHISTEntity(eventVo.getActionType());
        reviewHISTEntity.saveReview(review);
        reviewHISTEntity.setReviewPoint(reviewPoint);
        reviewHISTEntityRepository.save(reviewHISTEntity);
    }
    private void modifiedReview(EventVo eventVo) {

    }

    private void deleteReview(EventVo eventVo) {

    }

    public void selectDurationPoint(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<ReviewPointListDto> search = reviewEntityRepository.search(userId, startDate, endDate);
    }
}
