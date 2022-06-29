package com.triple.clubmileageservice.domain;

import org.springframework.util.StringUtils;

import java.util.List;

public class PointCalculator {

    public int addContentPoint(String content) {
        return StringUtils.hasText(content) ? 1 : 0;
    }

    public int addPhotoPoint(List<String> attachedPhotoIds) {
        if (attachedPhotoIds == null) {
            return 0;
        }
        return attachedPhotoIds.size() > 0 ? 1 : 0;
    }
}
