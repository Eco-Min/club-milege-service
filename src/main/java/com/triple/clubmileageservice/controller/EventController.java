package com.triple.clubmileageservice.controller;

import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.dto.PointDto;
import com.triple.clubmileageservice.service.ReviewEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ReviewEventService reviewEventService;

    @PostMapping("/events")
    public PointDto events(@RequestBody EventDto eventDto){
        reviewEventService.createReview(eventDto);
        return new PointDto();
    }
}
