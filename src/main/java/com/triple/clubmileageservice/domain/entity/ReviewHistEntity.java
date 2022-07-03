package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "review_hist",
        indexes = {@Index(name="i_createAt", columnList = "create_At"),
                @Index(name="i_reviewHistNo", columnList = "review_hist_no")})
@NoArgsConstructor
public class ReviewHistEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_hist_no")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_no")
    private ReviewEntity review;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private ActionType actionType;

    @Column(name = "review_point")
    private int reviewPoint;

    @Column(name = "bonus_point")
    private int bonusPoint;

    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

    public ReviewHistEntity(ActionType add) {
        this.actionType = add;
    }


    public void setReviewPoint(int reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public void saveReview(ReviewEntity review) {
        if (this.review != null) {
            this.review.getReviewHISTs().remove(this);
        }
        this.review = review;
        review.getReviewHISTs().add(this);
    }
    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
    }

    public void setBonusPoint(int bonusPoint) {
        this.bonusPoint = bonusPoint;
    }
}
