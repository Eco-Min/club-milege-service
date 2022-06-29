package com.triple.clubmileageservice.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "place")
public class PlaceEntity {
    @Id
    @Column(name = "place_id")
    private String id;

    @Column(name = "place_name")
    private String placeName;

    @OneToMany(mappedBy = "place")
    private List<ReviewEntity> reviews;
}
