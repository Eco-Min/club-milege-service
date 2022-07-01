package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "photo")
@NoArgsConstructor
public class PhotoEntity {

    @Id
    @Column(name = "photo_id")
    private String id;

    @Column(name = "photo_file_path")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
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
