package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "place")
public class PlaceEntity extends BaseTimeEntity{
    @Id
    @Column(name = "place_id")
    private String id;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "place_description")
    private String description;

    @OneToMany(mappedBy = "place")
    private List<ReviewEntity> reviews = new ArrayList<>();
}
