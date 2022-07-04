package com.triple.clubmileageservice.repository.query;

import com.triple.clubmileageservice.dto.ReviewPointListDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewEventRepository {
    List<ReviewPointListDto> searchUserDurationPoint(String userId, LocalDateTime startDate, LocalDateTime endDate);

    int checkPlaceBonbonsPoint(Long placeNo);

    Integer userHasPointInPlace(Long reviewNo);

    int userHasBonusPointInPlace(Long reviewNo);

    Integer allPointFromUser(String userId);
}
