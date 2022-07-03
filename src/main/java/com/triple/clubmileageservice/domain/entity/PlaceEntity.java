package com.triple.clubmileageservice.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "place", indexes = @Index(name="i_placeId", columnList = "place_id"))
@NoArgsConstructor
public class PlaceEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "place_no")
    private Long placeNo;

    @Column(name = "place_id", unique = true)
    private String placeId;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "place_description")
    private String description;

    @OneToMany(mappedBy = "place")
    private List<ReviewEntity> reviews = new ArrayList<>();

    public PlaceEntity(String placeId, String placeName, String description) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.description = description;
    }
}
