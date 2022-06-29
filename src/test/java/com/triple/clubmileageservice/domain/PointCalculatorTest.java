/*
package com.triple.clubmileageservice.domain;

import com.triple.clubmileageservice.dto.EventDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class PointCalculatorTest {

    private EventDto eventDto1;
    private EventDto eventDto2;

    @BeforeEach
    public void beforeEach() {
        eventDto1 = EventDto.builder()
                .type("REVIEW")
                .action("ADD")
                .content("")
                .attachedPhotoIds(new ArrayList<>())
                .userId(UUID.randomUUID().toString())
                .placeId(UUID.randomUUID().toString())
                .build();

        eventDto2 = EventDto.builder()
                .type("REVIEW")
                .action("ADD")
                .content("Hello")
                .attachedPhotoIds(List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString()))
                .userId(UUID.randomUUID().toString())
                .placeId(UUID.randomUUID().toString())
                .build();
    }

    @Test
    @DisplayName("1자이상 텍스트, 사진 첨부 1점 추가")
    public void eventContentAllocationPointPlus() throws Exception{
        //given
        eventDto1.setContent("1자 이상");
        eventDto1.setAttachedPhotoIds(List.of(UUID.randomUUID().toString()));
        PointCalculator pointCalculator = new PointCalculator();

        //when
        int getContentPoint = pointCalculator.addContent(eventDto1.getContent());
        int getPhotoPoint = pointCalculator.addPhoto(eventDto1.getAttachedPhotoIds());

        //then
        assertThat(getContentPoint).isEqualTo(1);
        assertThat(getPhotoPoint).isEqualTo(1);
     }

    @Test
    @DisplayName("1자이상 텍스트, 사진 미첨부 0점 부여")
    public void eventContentAllocationPointZero() throws Exception{
        //given
        PointCalculator pointCalculator = new PointCalculator();

        //when
        int getContentPoint = pointCalculator.addContent(eventDto1.getContent());
        int getPhotoPoint = pointCalculator.addPhoto(eventDto1.getAttachedPhotoIds());

        //then
        assertThat(getContentPoint).isEqualTo(0);
        assertThat(getPhotoPoint).isEqualTo(0);
    }

    @Test
    @DisplayName("content 삭제시 1점 차감")
    public void deleteContent() throws Exception{
        //given
        PointCalculator pointCalculator = new PointCalculator();

        //when
        int deleteContent = pointCalculator.deleteContent();

        //then

     }

    @Test
    @DisplayName("photo 삭제시 1점 차감")
    public void deletePhoto() throws Exception{
        //given
        PointCalculator pointCalculator = new PointCalculator();

        //when
        int deletePhoto = pointCalculator.deletePhoto();

        //then

    }

    @Test
    @DisplayName("글만 작성한 리뷰에 사진을 추가하면 1점부여")
    public void eventContentDeleteAllocation() throws Exception{
        //given
        PointCalculator pointCalculator = new PointCalculator();

        //when
        pointCalculator.modifiedContent("");
        pointCalculator.modifiedPhoto(List.of(""));

        //then


     }

}
*/
