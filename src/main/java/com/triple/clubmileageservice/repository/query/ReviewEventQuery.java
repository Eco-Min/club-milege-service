package com.triple.clubmileageservice.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.clubmileageservice.domain.entity.PlaceEntity;
import com.triple.clubmileageservice.dto.QReviewPointListDto;
import com.triple.clubmileageservice.dto.ReviewPointListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.triple.clubmileageservice.domain.entity.QPlaceEntity.placeEntity;
import static com.triple.clubmileageservice.domain.entity.QReviewEntity.reviewEntity;
import static com.triple.clubmileageservice.domain.entity.QReviewHistEntity.reviewHistEntity;


@Repository
@RequiredArgsConstructor
public class ReviewEventQuery implements ReviewEventRepository {

    private final JPAQueryFactory factory;

    @Override
    public List<ReviewPointListDto> searchUserDurationPoint(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        return factory.select(new QReviewPointListDto(
                        placeEntity.placeId, reviewEntity.reviewId, reviewHistEntity.reviewPoint, reviewHistEntity.createAt
                ))
                .from(reviewEntity)
                .leftJoin(reviewEntity.place, placeEntity)
                .leftJoin(reviewEntity.reviewHISTs, reviewHistEntity)
                .where(userIdEq(userId),
                        betweenPointHistDate(startDate, endDate)
                ).fetch();
    }

    @Override
    public int placeBonbonsPoint(Long placeNo) {
        Integer bonusPoint = factory.select(reviewHistEntity.bonusPoint.sum())
                .from(reviewEntity)
                .leftJoin(reviewEntity.place, placeEntity)
                .leftJoin(reviewEntity.reviewHISTs, reviewHistEntity)
                .where(placeEntity.placeNo.eq(placeNo))
                .fetchOne();
        if (bonusPoint == null || bonusPoint == 0) {
            bonusPoint = 1;
        } else bonusPoint = 0;
        return bonusPoint;
    }

    @Override
    public Integer checkPoint(Long reviewNo) {
        return factory.select(reviewHistEntity.reviewPoint.sum())
                .from(reviewEntity)
                .leftJoin(reviewEntity.place, placeEntity).fetchJoin()
                .where(reviewEntity.reviewNo.eq(reviewNo))
                .fetchOne();
    }

    @Override
    public int hasBonusPoint(Long reviewNo) {
        Integer bonusPoint = factory.select(reviewHistEntity.bonusPoint.sum())
                .from(reviewEntity)
                .leftJoin(reviewEntity.place, placeEntity)
                .where(reviewEntity.reviewNo.eq(reviewNo))
                .fetchOne();
        if (bonusPoint == null || bonusPoint == 0) {
            bonusPoint = 0;
        } else bonusPoint = -1;
        return bonusPoint;
    }

    public BooleanExpression betweenPointHistDate(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate != null && endDate != null ? reviewHistEntity.createAt.between(startDate, endDate) : null;
    }

    public BooleanExpression userIdEq(String userId) {
        return userId != null ? reviewEntity.user.userId.eq(userId) : null;
    }
}
