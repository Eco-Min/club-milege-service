package com.triple.clubmileageservice.domain;

import org.springframework.util.StringUtils;

import java.util.List;

public class PointCalculator {

    public int addContentAndPhotos(String content, List<String> attachedPhotoIds, String firstReview) {
        return addContent(content) + addPhoto(attachedPhotoIds);
    }

    public int addContent(String content) {
        return StringUtils.hasText(content) ? 1 : 0;
    }

    public int addPhoto(List<String> attachedPhotoIds) {
        if (attachedPhotoIds == null) {
            return 0;
        }
        return attachedPhotoIds.size() > 0 ? 1 : 0;
    }

    public int modifiedContent(String content){
        return StringUtils.hasText(content) ? 0 : -1;
    }

    public int modifiedPhoto(List<String> attachedPhotoIds){
        if (attachedPhotoIds == null) {
            return -1;
        }
        return attachedPhotoIds.size() > 0 ? 0 : -1;
    }

    public int deleteContent() {
        return -1;
    }

    public int deletePhoto() {
        return -1;
    }
}
