package com.triple.clubmileageservice.dto;

import com.triple.clubmileageservice.domain.entity.ActionType;
import com.triple.clubmileageservice.vo.RequestEventVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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

    public static EventDto createEventVo(RequestEventVo eventVo) {
        ActionType newActionType = null;
        switch (eventVo.getAction()) {
            case "ADD" -> newActionType = ActionType.ADD;
            case "MOD" -> newActionType = ActionType.MOD;
            case "DELETE" -> newActionType = ActionType.DELETE;
        }
        return new EventDto(eventVo.getType(), newActionType, eventVo.getReviewId(), eventVo.getContent(),
                eventVo.getAttachedPhotoIds(), eventVo.getUserId(), eventVo.getPlaceId());
    }

}
