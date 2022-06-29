package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @Column(name = "photo_id")
    private String id;

    @Column(name = "photo_file_path")
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "review_id")
    ReviewEntity review;

    private void saveReview(ReviewEntity review) {
        if (this.review != null) {
            this.review.getPhotos().remove(this);
        }
        this.review = review;
        review.getPhotos().add(this);
    }
}
