package com.triple.clubmileageservice.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "review_entity")
public class ReviewEntity extends BaseTimeEntity{

    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "review_content")
    private String content;

    @OneToMany(mappedBy = "review")
    private List<PhotoEntity> photos;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private void saveUser(UserEntity user) {

        if (this.user != null) {
            this.user.getReviews().remove(this);
        }
        this.user = user;
        user.getReviews().add(this);
    }

    private void savePlace(PlaceEntity place) {

        if (this.place != null) {
            this.place.getReviews().remove(this);
        }
        this.place = place;
        place.getReviews().add(this);
    }
}
