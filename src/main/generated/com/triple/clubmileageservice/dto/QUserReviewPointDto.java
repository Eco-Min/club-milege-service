package com.triple.clubmileageservice.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.triple.clubmileageservice.dto.QUserReviewPointDto is a Querydsl Projection type for UserReviewPointDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserReviewPointDto extends ConstructorExpression<UserReviewPointDto> {

    private static final long serialVersionUID = 577343947L;

    public QUserReviewPointDto(com.querydsl.core.types.Expression<Integer> bonusPoint, com.querydsl.core.types.Expression<Integer> reviewPoint) {
        super(UserReviewPointDto.class, new Class<?>[]{int.class, int.class}, bonusPoint, reviewPoint);
    }

}

