package com.triple.clubmileageservice.service.domain;

import com.triple.clubmileageservice.domain.entity.PlaceEntity;
import com.triple.clubmileageservice.dto.PlaceDto;
import com.triple.clubmileageservice.repository.PlaceEntityRepository;
import com.triple.clubmileageservice.reqres.PlaceRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceEntityRepository placeEntityRepository;

    public List<PlaceRes> findAllPlace() {
        List<PlaceEntity> findAllPlace = placeEntityRepository.findAll();

        return findAllPlace.stream()
                .map(place -> new PlaceRes(place.getPlaceId(), place.getPlaceName()))
                .toList();
    }

    public PlaceRes createPlace(PlaceDto place) {
        PlaceEntity placeEntity = new PlaceEntity(place.getPlaceId(), place.getPlaceName(), place.getDescription());
        PlaceEntity save = placeEntityRepository.save(placeEntity);
        return new PlaceRes(save.getPlaceId(), save.getPlaceName());
    }

    public PlaceEntity findPlaceId(String placeId) {
        return placeEntityRepository.findByPlaceId(placeId);
    }
}
