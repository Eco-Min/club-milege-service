package com.triple.clubmileageservice.reqres;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceRes {
    private String placeId;
    private String placeName;

    public PlaceRes(String placeId, String placeName) {
        this.placeId = placeId;
        this.placeName = placeName;
    }
}
