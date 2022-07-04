package com.triple.clubmileageservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserReviewPointDto {
    private Integer bonusPoint;
    private Integer reviewPoint;

    @QueryProjection
    public UserReviewPointDto(Integer bonusPoint, Integer reviewPoint) {
        this.bonusPoint = bonusPoint != null ? bonusPoint : 0;
        this.reviewPoint = reviewPoint != null ? reviewPoint : 0;
    }
}
