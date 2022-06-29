package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "review_hist")
public class ReviewHISTEntity {

    @Id
    @Column(name = "review_hist_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private ReviewEntity review;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private ActionType actionType;

    @Column(name= "create_at", updatable = false)
    private LocalDateTime createAt;

    public ReviewHISTEntity(long l, ActionType add) {
        this.id = l;
        this.actionType = add;
    }

    public ReviewHISTEntity() {

    }

    public void saveReview(ReviewEntity review) {
        if (this.review != null) {
            this.review.getReviewHISTs().remove(this);
        }
        this.review = review;
        review.getReviewHISTs().add(this);
    }
}
