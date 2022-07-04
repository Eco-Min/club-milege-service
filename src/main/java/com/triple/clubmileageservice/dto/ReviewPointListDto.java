package com.triple.clubmileageservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.triple.clubmileageservice.domain.entity.ActionType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewPointListDto {
    private String placeId;
    private String reviewId;

    private ActionType actionType;

    private int bonusPoint;
    private int reviewPoint;
    private LocalDateTime date;

    @QueryProjection

    public ReviewPointListDto(String placeId, String reviewId, ActionType actionType, int bonusPoint, int reviewPoint, LocalDateTime date) {
        this.placeId = placeId;
        this.reviewId = reviewId;
        this.actionType = actionType;
        this.bonusPoint = bonusPoint;
        this.reviewPoint = reviewPoint;
        this.date = date;
    }
}
