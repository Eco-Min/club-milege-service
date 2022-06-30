package com.triple.clubmileageservice.vo;

import com.triple.clubmileageservice.domain.entity.ActionType;
import com.triple.clubmileageservice.dto.EventDto;
import lombok.*;

import javax.swing.*;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class EventVo {
    private String type;
    private ActionType actionType;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;

    public static EventVo createEventVo(EventDto eventDto) {
        ActionType newActionType = null;
        switch (eventDto.getAction()) {
            case "ADD" -> newActionType = ActionType.ADD;
            case "MOD" -> newActionType = ActionType.MOD;
            case "DELETE" -> newActionType = ActionType.DELETE;
        }
        return new EventVo(eventDto.getType(), newActionType, eventDto.getReviewId(), eventDto.getContent(),
                eventDto.getAttachedPhotoIds(), eventDto.getUserId(), eventDto.getPlaceId());
    }


}
