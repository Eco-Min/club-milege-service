package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor
public class ReviewEntity extends BaseTimeEntity{

    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "review_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "review")
    private List<PhotoEntity> photos = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    List<ReviewHISTEntity> reviewHISTs = new ArrayList<>();

    @Column(name = "use_YN")
    @Size(max = 1)
    private String useYN;

    public ReviewEntity(String reviewId, String content) {
        this.reviewId = reviewId;
        this.content = content;
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
