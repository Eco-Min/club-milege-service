package com.triple.clubmileageservice.vo;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestEventVo {

    private String type;
    private String action;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;
}
