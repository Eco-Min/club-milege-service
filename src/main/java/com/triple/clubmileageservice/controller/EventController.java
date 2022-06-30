package com.triple.clubmileageservice.controller;

import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.dto.PointDto;
import com.triple.clubmileageservice.service.ReviewEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ReviewEventService reviewEventService;

    @PostMapping("/events")
    public String events(@RequestBody EventDto eventDto) {
        reviewEventService.events(eventDto);
        return "OK";
    }

    @GetMapping("/selectPoint")
    public void selectPoint(@RequestParam String userId,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
                            ){
        reviewEventService.selectDurationPoint(userId, startDate, endDate);
    }
}
