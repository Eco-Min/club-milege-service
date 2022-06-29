package com.triple.clubmileageservice.service;

import com.triple.clubmileageservice.domain.entity.*;
import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewEventService {
    private final ReviewEntityRepository reviewEntityRepository;
    private final ReviewHISTEntityRepository reviewHISTEntityRepository;
    private final PhotoRepository photoRepository;
    private final UserEntityRepository userEntityRepository;
    private final PlaceEntityRepository placeEntityRepository;

    @Transactional
    public void createReview(EventDto eventDto) {
        UserEntity userEntity = userEntityRepository.findById(eventDto.getUserId()).get();
        PlaceEntity placeEntity = placeEntityRepository.findById(eventDto.getPlaceId()).get();

        ReviewEntity review = new ReviewEntity(eventDto.getReviewId(), eventDto.getContent());
        review.saveUser(userEntity);
        review.savePlace(placeEntity);
        reviewEntityRepository.save(review);

        ReviewHISTEntity reviewHISTEntity = new ReviewHISTEntity(1L, ActionType.ADD);
        reviewHISTEntity.saveReview(review);
        reviewHISTEntityRepository.save(reviewHISTEntity);

    }
}
