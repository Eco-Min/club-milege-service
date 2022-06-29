package com.triple.clubmileageservice.repository;

import com.triple.clubmileageservice.domain.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceEntityRepository extends JpaRepository<PlaceEntity, String> {
}
