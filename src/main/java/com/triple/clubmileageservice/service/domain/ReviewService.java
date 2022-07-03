package com.triple.clubmileageservice.service.domain;

import com.triple.clubmileageservice.domain.entity.ReviewEntity;
import com.triple.clubmileageservice.repository.ReviewEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewEntityRepository reviewEntityRepository;

    public ReviewEntity findByReviewId(String reviewId) {
        return reviewEntityRepository.findByReviewId(reviewId);
    }

    public ReviewEntity findByReviewIdAndUseYN(String reviewId) {
        return reviewEntityRepository.findByReviewIdAndUseYN(reviewId, "Y");
    }

    public void save(ReviewEntity review) {
        reviewEntityRepository.save(review);
    }

}
