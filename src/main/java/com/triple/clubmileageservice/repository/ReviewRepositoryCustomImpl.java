package com.triple.clubmileageservice.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.clubmileageservice.domain.entity.QPlaceEntity;
import com.triple.clubmileageservice.domain.entity.QReviewEntity;
import com.triple.clubmileageservice.domain.entity.QReviewHISTEntity;
import com.triple.clubmileageservice.dto.QReviewPointListDto;
import com.triple.clubmileageservice.dto.ReviewPointListDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.triple.clubmileageservice.domain.entity.QPlaceEntity.*;
import static com.triple.clubmileageservice.domain.entity.QReviewEntity.*;
import static com.triple.clubmileageservice.domain.entity.QReviewHISTEntity.*;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory factory;

    @Override
    public List<ReviewPointListDto> search(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        return factory.select(new QReviewPointListDto(
                         placeEntity.id, reviewEntity.reviewId, reviewHISTEntity.reviewPoint, reviewHISTEntity.createAt
                 ))
                .from(reviewEntity)
                .leftJoin(reviewEntity.place, placeEntity)
                .leftJoin(reviewEntity.reviewHISTs, reviewHISTEntity)
                .where(userIdEq(userId),
                        betweenPointHistDate(startDate, endDate)
                ).fetch();
    }

    private BooleanExpression betweenPointHistDate(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate != null && endDate !=null ? reviewHISTEntity.createAt.between(startDate, endDate): null;
    }

    private BooleanExpression userIdEq(String userId) {
        return userId != null ? reviewEntity.user.id.eq(userId) : null;
    }
}
