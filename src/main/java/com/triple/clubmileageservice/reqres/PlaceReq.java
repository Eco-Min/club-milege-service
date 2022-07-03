package com.triple.clubmileageservice.reqres;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceReq {
    private String placeName;
    private String description;

    public PlaceReq(String placeName, String description) {
        this.placeName = placeName;
        this.description = description;
    }
}
