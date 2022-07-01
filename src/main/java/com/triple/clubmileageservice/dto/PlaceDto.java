package com.triple.clubmileageservice.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PlaceDto {
    private String placeId;
    private String placeName;
    private String description;

    private PlaceDto(String placeId, String placeName, String description) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.description = description;
    }

    public static PlaceDto createPlace(String placeName, String description) {
        return new PlaceDto(UUID.randomUUID().toString(), placeName, description);
    }
}
