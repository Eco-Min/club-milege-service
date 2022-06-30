package com.triple.clubmileageservice.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public class ReviewPointListDto {
    private String placeId;
    private String reviewId;
    private int reviewPoint;
    private LocalDateTime date;

    @QueryProjection
    public ReviewPointListDto(String placeId, String reviewId, int reviewPoint, LocalDateTime date) {
        this.placeId = placeId;
        this.reviewId = reviewId;
        this.reviewPoint = reviewPoint;
        this.date = date;
    }
}
