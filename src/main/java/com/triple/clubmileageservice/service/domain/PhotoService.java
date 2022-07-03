package com.triple.clubmileageservice.service.domain;

import com.triple.clubmileageservice.domain.entity.PhotoEntity;
import com.triple.clubmileageservice.repository.PhotoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoEntityRepository photoRepository;

    public List<PhotoEntity> findPhotoIds(Long reviewNo) {
        return photoRepository.findAllByReviewReviewNo(reviewNo);
    }
    public void saveAll(List<PhotoEntity> photos) {
        photoRepository.saveAll(photos);
    }

    public void deleteAllPhotoIds(List<String> attachedPhotoIds) {
        photoRepository.deleteAllByPhotoIdIn(attachedPhotoIds);
    }
}
