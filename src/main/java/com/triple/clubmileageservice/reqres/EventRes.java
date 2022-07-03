package com.triple.clubmileageservice.reqres;
import com.triple.clubmileageservice.dto.EventDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventRes {

    private int reviewPoint;
    private int bonusPoint;
    private EventReq eventReq;

    public EventRes(int reviewPoint, int bonusPoint, EventDto eventDto) {
        this.reviewPoint = reviewPoint;
        this.bonusPoint = bonusPoint;
        this.eventReq = getEventReq(eventDto);
    }

    private EventReq getEventReq(EventDto eventDto) {
        String action;
        switch (eventDto.getActionType()) {
            case MOD -> action = "ADD";
            case ADD -> action = "MOD";
            case DELETE -> action = "DELETE";
            default -> action = "";
        }
        return new EventReq(eventDto.getType(), action, eventDto.getReviewId(), eventDto.getContent(),
                eventDto.getAttachedPhotoIds(), eventDto.getUserId(), eventDto.getPlaceId());
    }
}
