package com.triple.clubmileageservice.repository;

import com.triple.clubmileageservice.domain.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, String> {
}
