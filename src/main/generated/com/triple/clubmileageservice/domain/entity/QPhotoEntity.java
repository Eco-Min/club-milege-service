package com.triple.clubmileageservice.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhotoEntity is a Querydsl query type for PhotoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhotoEntity extends EntityPathBase<PhotoEntity> {

    private static final long serialVersionUID = 85541194L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhotoEntity photoEntity = new QPhotoEntity("photoEntity");

    public final StringPath filePath = createString("filePath");

    public final StringPath photoId = createString("photoId");

    public final NumberPath<Long> photoNo = createNumber("photoNo", Long.class);

    public final QReviewEntity review;

    public QPhotoEntity(String variable) {
        this(PhotoEntity.class, forVariable(variable), INITS);
    }

    public QPhotoEntity(Path<? extends PhotoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhotoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhotoEntity(PathMetadata metadata, PathInits inits) {
        this(PhotoEntity.class, metadata, inits);
    }

    public QPhotoEntity(Class<? extends PhotoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReviewEntity(forProperty("review"), inits.get("review")) : null;
    }

}

