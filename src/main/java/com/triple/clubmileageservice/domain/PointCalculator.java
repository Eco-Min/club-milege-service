package com.triple.clubmileageservice.domain;

import com.triple.clubmileageservice.dto.EventDto;
import org.springframework.util.StringUtils;

import java.util.List;

public class PointCalculator {

    public int addContentAndPhotos(String content, List<String> attachedPhotoIds, String firstReview) {
        return addContent(content) + addPhoto(attachedPhotoIds);
    }

    public int modifiedContentAndPhotos(EventDto oldEventDto, EventDto eventDto) {
        return modifiedContent(oldEventDto.getContent(), eventDto.getContent()) +
                modifiedPhoto(oldEventDto.getAttachedPhotoIds(), eventDto.getAttachedPhotoIds());
    }

    private int addContent(String content) {
        return StringUtils.hasText(content) ? 1 : 0;
    }

    private int addPhoto(List<String> attachedPhotoIds) {
        if (attachedPhotoIds == null) {
            return 0;
        }
        return attachedPhotoIds.size() > 0 ? 1 : 0;
    }

    private int modifiedContent(String oldContent, String newContent) {
        if (!StringUtils.hasText(oldContent) && StringUtils.hasText(newContent)) {
            return 1;
        } else if (StringUtils.hasText(oldContent) && !StringUtils.hasText(newContent)) {
            return -1;
        } else {
            return 0;
        }
    }

    private int modifiedPhoto(List<String> oldAttachedPhotoIds, List<String> newAttachedPhotoIds) {
        if (oldAttachedPhotoIds.size() == 0 && newAttachedPhotoIds.size() > 0) {
            return 1;
        } else if (oldAttachedPhotoIds.size() > 0 && newAttachedPhotoIds.size() == 0) {
            return -1;
        } else {
            return 0;
        }
    }

    private int deleteContent() {
        return -1;
    }

    private int deletePhoto() {
        return -1;
    }

}
