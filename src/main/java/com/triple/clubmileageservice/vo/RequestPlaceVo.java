package com.triple.clubmileageservice.vo;

import lombok.Getter;

@Getter
public class RequestPlaceVo {
    private String placeName;
    private String description;

    public RequestPlaceVo(String placeName, String description) {
        this.placeName = placeName;
        this.description = description;
    }
}
