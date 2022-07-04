package com.triple.clubmileageservice.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewHistEntity is a Querydsl query type for ReviewHistEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewHistEntity extends EntityPathBase<ReviewHistEntity> {

    private static final long serialVersionUID = 822462376L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewHistEntity reviewHistEntity = new QReviewHistEntity("reviewHistEntity");

    public final EnumPath<ActionType> actionType = createEnum("actionType", ActionType.class);

    public final NumberPath<Integer> bonusPoint = createNumber("bonusPoint", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReviewEntity review;

    public final NumberPath<Integer> reviewPoint = createNumber("reviewPoint", Integer.class);

    public QReviewHistEntity(String variable) {
        this(ReviewHistEntity.class, forVariable(variable), INITS);
    }

    public QReviewHistEntity(Path<? extends ReviewHistEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewHistEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewHistEntity(PathMetadata metadata, PathInits inits) {
        this(ReviewHistEntity.class, metadata, inits);
    }

    public QReviewHistEntity(Class<? extends ReviewHistEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReviewEntity(forProperty("review"), inits.get("review")) : null;
    }

}

