package com.triple.clubmileageservice.service;

import com.triple.clubmileageservice.domain.entity.PlaceEntity;
import com.triple.clubmileageservice.dto.PlaceDto;
import com.triple.clubmileageservice.repository.PlaceEntityRepository;
import com.triple.clubmileageservice.vo.ResponsePlaceVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceEntityRepository placeEntityRepository;

    public List<ResponsePlaceVo> findAllPlace() {
        List<PlaceEntity> findAllPlace = placeEntityRepository.findAll();

        return findAllPlace.stream()
                .map(place -> new ResponsePlaceVo(place.getId(), place.getPlaceName()))
                .toList();
    }

    public ResponsePlaceVo createPlace(PlaceDto place) {
        PlaceEntity placeEntity = new PlaceEntity(place.getPlaceId(), place.getPlaceName(), place.getDescription());
        PlaceEntity save = placeEntityRepository.save(placeEntity);
        return new ResponsePlaceVo(save.getId(), save.getPlaceName());
    }
}
