package com.triple.clubmileageservice.service.domain;

import com.triple.clubmileageservice.domain.entity.ReviewHistEntity;
import com.triple.clubmileageservice.repository.ReviewHistEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewHistService {

    private final ReviewHistEntityRepository reviewHistEntityRepository;

    public void save(ReviewHistEntity reviewHISTEntity) {
        reviewHistEntityRepository.save(reviewHISTEntity);
    }
}
