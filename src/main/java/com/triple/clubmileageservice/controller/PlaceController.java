package com.triple.clubmileageservice.controller;

import com.triple.clubmileageservice.dto.PlaceDto;
import com.triple.clubmileageservice.service.domain.PlaceService;
import com.triple.clubmileageservice.reqres.PlaceReq;
import com.triple.clubmileageservice.reqres.PlaceRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping("/createPlace")
    public PlaceRes createPlace(@RequestBody PlaceReq placeReq) {
        PlaceDto place = PlaceDto.createPlace(placeReq.getPlaceName(), placeReq.getDescription());
        return placeService.createPlace(place);
    }

    @GetMapping("/findAllPlace")
    public List<PlaceRes> findAllUser() {
        return placeService.findAllPlace();
    }
}
