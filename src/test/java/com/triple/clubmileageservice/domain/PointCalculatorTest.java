package com.triple.clubmileageservice.domain;

import com.triple.clubmileageservice.dto.EventDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class PointCalculatorTest {

    @Test
    @DisplayName("1자이상 텍스트 작성 1점, photo 1장이상 사진첨부 1점")
    public void addContentAndPhoto() throws Exception{
        //given
        String content = "좋아요!";
        List<String> photoList = List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        
        //when
        PointCalculator pointCalculator = new PointCalculator();
        int point = pointCalculator.addContentAndPhotos(content, photoList, null);

        //then
        assertThat(point).isEqualTo(2);
     }
     
     @Test
     @DisplayName("글만 작성한 리뷰에 사진추가 1점부여")
     public void addPhotoToReviewHasOnlyContent() throws Exception{
         //given
         List<String> photoList = List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString());
         EventDto oldEventDto = EventDto.builder().content("좋아요!").attachedPhotoIds(new ArrayList<>()).build();
         EventDto newEventDto = EventDto.builder().content("좋아요!").attachedPhotoIds(photoList).build();

         //when
         PointCalculator pointCalculator = new PointCalculator();
         int point = pointCalculator.modifiedContentAndPhotos(oldEventDto, newEventDto);

         //then
         Assertions.assertThat(point).isEqualTo(1);
      }
}