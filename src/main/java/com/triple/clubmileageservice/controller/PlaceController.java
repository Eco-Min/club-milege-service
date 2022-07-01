package com.triple.clubmileageservice.controller;

import com.triple.clubmileageservice.dto.PlaceDto;
import com.triple.clubmileageservice.service.PlaceService;
import com.triple.clubmileageservice.vo.RequestPlaceVo;
import com.triple.clubmileageservice.vo.ResponsePlaceVo;
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
    public ResponsePlaceVo createUser(@RequestBody RequestPlaceVo requestPlaceVo) {
        PlaceDto place = PlaceDto.createPlace(requestPlaceVo.getPlaceName(), requestPlaceVo.getDescription());
        return placeService.createPlace(place);
    }

    @GetMapping("/findAllPlace")
    public List<ResponsePlaceVo> findAllUser() {
        return placeService.findAllPlace();
    }
}
