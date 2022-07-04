package com.triple.clubmileageservice.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlaceEntity is a Querydsl query type for PlaceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceEntity extends EntityPathBase<PlaceEntity> {

    private static final long serialVersionUID = -1014060961L;

    public static final QPlaceEntity placeEntity = new QPlaceEntity("placeEntity");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath placeId = createString("placeId");

    public final StringPath placeName = createString("placeName");

    public final NumberPath<Long> placeNo = createNumber("placeNo", Long.class);

    public final ListPath<ReviewEntity, QReviewEntity> reviews = this.<ReviewEntity, QReviewEntity>createList("reviews", ReviewEntity.class, QReviewEntity.class, PathInits.DIRECT2);

    public QPlaceEntity(String variable) {
        super(PlaceEntity.class, forVariable(variable));
    }

    public QPlaceEntity(Path<? extends PlaceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlaceEntity(PathMetadata metadata) {
        super(PlaceEntity.class, metadata);
    }

}

