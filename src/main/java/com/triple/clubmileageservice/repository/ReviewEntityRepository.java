package com.triple.clubmileageservice.repository;

import com.triple.clubmileageservice.domain.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Long>{
    ReviewEntity findByReviewIdAndUseYN(String reviewId, String Y);
    ReviewEntity findByReviewId(String reviewId);
}
