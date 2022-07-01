package com.triple.clubmileageservice.controller;

import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.dto.ReviewPointListDto;
import com.triple.clubmileageservice.service.ReviewEventService;
import com.triple.clubmileageservice.vo.RequestEventVo;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ReviewEventService reviewEventService;

    @PostMapping("/events")
    public String events(@RequestBody RequestEventVo eventVo) {
        EventDto eventDto = EventDto.createEventVo(eventVo);
        reviewEventService.events(eventDto);
        return "OK";
    }

    @GetMapping("/selectPoint")
    public List<ReviewPointListDto> selectPoint(@RequestParam String userId,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
                            ){
        return reviewEventService.selectDurationPoint(userId, startDate, endDate);
    }
}
