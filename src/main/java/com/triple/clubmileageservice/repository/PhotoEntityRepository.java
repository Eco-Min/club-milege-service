package com.triple.clubmileageservice.repository;

import com.triple.clubmileageservice.domain.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoEntityRepository extends JpaRepository<PhotoEntity, Long> {
    List<PhotoEntity> findAllByReviewReviewNo(Long reviewNo);

    List<PhotoEntity> deleteAllByPhotoIdIn(List<String> attachedPhotoIds);
}
