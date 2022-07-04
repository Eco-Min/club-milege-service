package com.triple.clubmileageservice.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.triple.clubmileageservice.dto.QReviewPointListDto is a Querydsl Projection type for ReviewPointListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewPointListDto extends ConstructorExpression<ReviewPointListDto> {

    private static final long serialVersionUID = 440491938L;

    public QReviewPointListDto(com.querydsl.core.types.Expression<String> placeId, com.querydsl.core.types.Expression<String> reviewId, com.querydsl.core.types.Expression<com.triple.clubmileageservice.domain.entity.ActionType> actionType, com.querydsl.core.types.Expression<Integer> bonusPoint, com.querydsl.core.types.Expression<Integer> reviewPoint, com.querydsl.core.types.Expression<java.time.LocalDateTime> date) {
        super(ReviewPointListDto.class, new Class<?>[]{String.class, String.class, com.triple.clubmileageservice.domain.entity.ActionType.class, int.class, int.class, java.time.LocalDateTime.class}, placeId, reviewId, actionType, bonusPoint, reviewPoint, date);
    }

}

