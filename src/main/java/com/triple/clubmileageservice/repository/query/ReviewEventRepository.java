package com.triple.clubmileageservice.repository.query;

import com.triple.clubmileageservice.dto.ReviewPointListDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewEventRepository {
    List<ReviewPointListDto> searchUserDurationPoint(String userId, LocalDateTime startDate, LocalDateTime endDate);

    int placeBonbonsPoint(Long placeNo);

    Integer checkPoint(Long reviewNo);

    int hasBonusPoint(Long reviewNo);
}
