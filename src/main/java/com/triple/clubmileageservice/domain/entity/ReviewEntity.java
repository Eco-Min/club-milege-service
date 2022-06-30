package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "review")
public class ReviewEntity extends BaseTimeEntity{

    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "review_content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<PhotoEntity> photos = new ArrayList<>();

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    List<ReviewHISTEntity> reviewHISTs = new ArrayList<>();

    @Column(name = "use_YN")
    @Size(max = 1)
    private String useYN;

    public ReviewEntity(String reviewId, String content) {
        this.reviewId = reviewId;
        this.content = content;
    }

    public ReviewEntity() {

    }

    @PrePersist
    private void prePersistUseYN() {
        this.useYN = "Y";
    }

    public void savePlace(PlaceEntity place) {
        if (this.place != null) {
            this.place.getReviews().remove(this);
        }
        this.place = place;
        place.getReviews().add(this);
    }

    public void saveUser(UserEntity userEntity) {
        /*if (this.user != null) {
            this.user.getReviews().remove(this);
        }*/
        this.user = userEntity;
//        userEntity.getReviews().add(this);
    }
}
