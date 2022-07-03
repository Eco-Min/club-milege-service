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
    @GeneratedValue
    @Column(name = "photo_no")
    private Long photoNo;

    @Column(name = "photo_id", unique = true)
    private String photoId;

    @Column(name = "photo_file_path")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    ReviewEntity review;

    public PhotoEntity(String photoId) {
        this.photoId = photoId;
    }

    public void saveReview(ReviewEntity review) {
        if (this.review != null) {
            this.review.getPhotos().remove(this);
        }
        this.review = review;
        review.getPhotos().add(this);
    }
}
