package com.triple.clubmileageservice.reqres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventReq {

    @NotNull
    private String type;
    @NotNull
    private String action;
    @NotNull
    private String reviewId;
    private String content;
    @NotNull
    private List<String> attachedPhotoIds;
    @NotNull
    private String userId;
    @NotNull
    private String placeId;
}
