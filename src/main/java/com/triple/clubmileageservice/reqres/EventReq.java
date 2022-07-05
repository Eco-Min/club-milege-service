package com.triple.clubmileageservice.reqres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventReq {

    @NotNull(message = "type 값이 없으면 안됩니다.")
    @NotEmpty(message = "type 비어있을 수 없습니다.")
    private String type;

    @NotNull(message = "action 값이 없으면 안됩니다.")
    @NotEmpty(message = "action 비어있을 수 없습니다.")
    private String action;

    @NotNull(message = "content 값이 없으면 안됩니다.")
    @NotEmpty(message = "content 비어있을 수 없습니다.")
    private String reviewId;

    @NotNull(message = "content 값이 없으면 안됩니다.")
    private String content;

    @NotNull(message = "attachedPhotoIds 값이 없으면 안됩니다.")
    private List<String> attachedPhotoIds;

    @NotNull(message = "userId 값이 없으면 안됩니다.")
    @NotEmpty(message = "userId 비어있을 수 없습니다.")
    private String userId;

    @NotNull(message = "placeId 값이 없으면 안됩니다.")
    @NotEmpty(message = "placeId 비어있을 수 없습니다.")
    private String placeId;
}
