package com.triple.clubmileageservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EventDto {

    private String type;
    private String action;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;
}
