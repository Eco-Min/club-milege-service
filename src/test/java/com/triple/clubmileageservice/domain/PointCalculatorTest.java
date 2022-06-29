package com.triple.clubmileageservice.domain;

import com.triple.clubmileageservice.domain.dto.EventDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PointCalculatorTest {

    private EventDto eventDto;

    @BeforeEach
    public void beforeEach() {
        eventDto = EventDto.builder()
                .type("REVIEW")
                .action("ADD")
                .content("")
                .attachedPhotoIds(new ArrayList<>())
                .userId(UUID.randomUUID().toString())
                .placeId(UUID.randomUUID().toString())
                .build();
    }

    @Test
    @DisplayName("내용 점수 : 1자이상 텍스트, 사진 첨부 1점 추가")
    public void eventContentAllocationPointPlus() throws Exception{
        //given
        eventDto.setContent("1자 이상");
        eventDto.setAttachedPhotoIds(List.of(UUID.randomUUID().toString()));
        PointCalculator pointCalculator = new PointCalculator();

        //when
        int getContentPoint = pointCalculator.addContentPoint(eventDto.getContent());
        int getPhotoPoint = pointCalculator.addPhotoPoint(eventDto.getAttachedPhotoIds());

        //then
        assertThat(getContentPoint).isEqualTo(1);
        assertThat(getPhotoPoint).isEqualTo(1);
     }

    @Test
    @DisplayName("내용 점수 : 1자이상 텍스트, 사진 첨부 0점 부여")
    public void eventContentAllocationPointZero() throws Exception{
        //given
        PointCalculator pointCalculator = new PointCalculator();

        //when
        int getContentPoint = pointCalculator.addContentPoint(eventDto.getContent());
        int getPhotoPoint = pointCalculator.addPhotoPoint(eventDto.getAttachedPhotoIds());

        //then
        assertThat(getContentPoint).isEqualTo(0);
        assertThat(getPhotoPoint).isEqualTo(0);
    }

}