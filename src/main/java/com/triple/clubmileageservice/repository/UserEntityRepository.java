package com.triple.clubmileageservice.repository;

import com.triple.clubmileageservice.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
}
