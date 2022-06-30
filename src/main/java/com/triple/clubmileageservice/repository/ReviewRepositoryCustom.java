package com.triple.clubmileageservice.repository;

import com.triple.clubmileageservice.dto.ReviewPointListDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepositoryCustom {
    List<ReviewPointListDto> search(String userId, LocalDateTime startDate, LocalDateTime endDate);
}
