package com.triple.clubmileageservice.repository;

import com.triple.clubmileageservice.domain.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoEntity, String> {
}
