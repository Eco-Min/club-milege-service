package com.triple.clubmileageservice.service;

import com.triple.clubmileageservice.domain.PointCalculator;
import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.dto.PlaceDto;
import com.triple.clubmileageservice.repository.query.ReviewEventRepository;
import com.triple.clubmileageservice.reqres.EventReq;
import com.triple.clubmileageservice.reqres.EventRes;
import com.triple.clubmileageservice.reqres.PlaceRes;
import com.triple.clubmileageservice.reqres.UserRes;
import com.triple.clubmileageservice.service.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback
class ReviewEventServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private ReviewEventService reviewEventService;

    PlaceRes placeRes;
    UserRes userRes1;
    UserRes userRes2;
    String reviewId1;
    String reviewId2;
    List<String> attachePhotoIds1;
    List<String> attachePhotoIds2;

    @BeforeEach
    public void create() {
        this.placeRes = placeService.createPlace(PlaceDto.createPlace("Test", "test"));
        this.userRes1 = userService.createUser("kjmin1@naver.com");
        this.userRes2 = userService.createUser("kjmin2@google.com");
        this.reviewId1 = UUID.randomUUID().toString();
        this.reviewId2 = UUID.randomUUID().toString();
        this.attachePhotoIds1 = List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        this.attachePhotoIds2 = List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    private EventDto eventDto(String reviewId1, String action1, String Add_Hello, List<String> attachePhotoIds1, UserRes userRes1) {
        EventReq eventReq = new EventReq("REVIEW", action1,
                reviewId1, Add_Hello,
                attachePhotoIds1,
                userRes1.getUserId(), placeRes.getPlaceId());
        return EventDto.createEventDto(eventReq);
    }

    @Test
    @DisplayName("ADD 장소 첫리뷰 Bonus 횟득")
    public void getAllReviewPointWithBonusPoint() throws Exception {
        //given
        EventDto eventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);

        //when
        EventRes events = reviewEventService.events(eventDto);

        //then
        EventRes eventRes = new EventRes(2, 1, eventDto);

        Assertions.assertThat(events.getReviewPoint()).isEqualTo(eventRes.getReviewPoint());
        Assertions.assertThat(events.getBonusPoint()).isEqualTo(eventRes.getBonusPoint());
        Assertions.assertThat(events.getEventReq().getReviewId()).isEqualTo(eventRes.getEventReq().getReviewId());
    }

    @Test
    @DisplayName("ADD 장소 리뷰 Bonus 횟득X content X")
    public void Add() throws Exception {
        //given
        EventDto oldEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        reviewEventService.events(oldEventDto);
        EventDto newEventDto = eventDto(reviewId2, "ADD", "", attachePhotoIds2, userRes2);

        //when
        EventRes events = reviewEventService.events(newEventDto);

        //then
        EventRes eventRes = new EventRes(1, 0, newEventDto);

        Assertions.assertThat(events.getReviewPoint()).isEqualTo(eventRes.getReviewPoint());
        Assertions.assertThat(events.getBonusPoint()).isEqualTo(eventRes.getBonusPoint());
        Assertions.assertThat(events.getEventReq().getReviewId()).isEqualTo(eventRes.getEventReq().getReviewId());
    }

    @Test
    @DisplayName("MOD Content O -> Content X")
    public void MODContentX() throws Exception {
        //given
        EventDto oldEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        EventRes events1 = reviewEventService.events(oldEventDto);
        System.out.println("events1.getBonusPoint() = " + events1.getBonusPoint());
        System.out.println("events1.getReviewPoint() = " + events1.getReviewPoint());
        EventDto newEventDto = eventDto(reviewId1, "MOD","", attachePhotoIds1, userRes1);

        //when
        EventRes events = reviewEventService.events(newEventDto);

        //then
        EventRes eventRes = new EventRes(-1, 0, newEventDto);

        Assertions.assertThat(events.getReviewPoint()).isEqualTo(eventRes.getReviewPoint());
        Assertions.assertThat(events.getBonusPoint()).isEqualTo(eventRes.getBonusPoint());
        Assertions.assertThat(events.getEventReq().getReviewId()).isEqualTo(eventRes.getEventReq().getReviewId());
    }

    @Test
    @DisplayName("MOD photo O -> photo X")
    public void MODPhotoX() throws Exception {
        //given
        EventDto oldEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        EventRes events1 = reviewEventService.events(oldEventDto);
        System.out.println("events1.getBonusPoint() = " + events1.getBonusPoint());
        System.out.println("events1.getReviewPoint() = " + events1.getReviewPoint());
        EventDto newEventDto = eventDto(reviewId1, "MOD","Add Hello", new ArrayList<>(), userRes1);

        //when
        EventRes events = reviewEventService.events(newEventDto);

        //then
        EventRes eventRes = new EventRes(-1, 0, newEventDto);

        Assertions.assertThat(events.getReviewPoint()).isEqualTo(eventRes.getReviewPoint());
        Assertions.assertThat(events.getBonusPoint()).isEqualTo(eventRes.getBonusPoint());
        Assertions.assertThat(events.getEventReq().getReviewId()).isEqualTo(eventRes.getEventReq().getReviewId());
    }

    @Test
    @DisplayName("삭제시 얻음 점수 전부 삭제")
    public void DELETE() throws Exception {
        //given

        //when


        //then

    }

}