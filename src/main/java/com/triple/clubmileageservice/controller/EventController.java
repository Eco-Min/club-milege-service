package com.triple.clubmileageservice.controller;

import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.dto.ReviewPointListDto;
import com.triple.clubmileageservice.reqres.EventReq;
import com.triple.clubmileageservice.reqres.EventRes;
import com.triple.clubmileageservice.reqres.UserPointRes;
import com.triple.clubmileageservice.service.ReviewEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ReviewEventService reviewEventService;

    @PostMapping("/events")
    public Object events(@Validated @RequestBody EventReq eventReq, BindingResult bindingResult) {
        EventDto eventDto = EventDto.createEventDto(eventReq);
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return reviewEventService.events(eventDto);
    }

    @GetMapping("/selectPointDuration")
    public List<ReviewPointListDto> selectPoint(@RequestParam String userId,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
    ) {
        return reviewEventService.selectDurationPoint(userId, startDate, endDate);
    }

    @GetMapping("/selectPoint/{userId}")
    public UserPointRes selectUserPoint(@PathVariable String userId) {
        return reviewEventService.selectUserPoint(userId);
    }
}
