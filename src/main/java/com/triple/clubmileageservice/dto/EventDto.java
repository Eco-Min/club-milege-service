package com.triple.clubmileageservice.dto;

import com.triple.clubmileageservice.domain.entity.ActionType;
import com.triple.clubmileageservice.reqres.EventReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class EventDto {
    private String type;
    private ActionType actionType;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;

    public static EventDto createEventDto(EventReq eventReq) {
        ActionType newActionType = null;
        switch (eventReq.getAction()) {
            case "ADD" -> newActionType = ActionType.ADD;
            case "MOD" -> newActionType = ActionType.MOD;
            case "DELETE" -> newActionType = ActionType.DELETE;
        }
        return new EventDto(eventReq.getType(), newActionType, eventReq.getReviewId(), eventReq.getContent(),
                eventReq.getAttachedPhotoIds(), eventReq.getUserId(), eventReq.getPlaceId());
    }

}
