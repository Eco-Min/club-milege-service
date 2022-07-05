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
    @DisplayName("ADD 장소 첫리뷰 Bonus 횟득 : 3점 부여")
    @Transactional
    @Rollback
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
    @DisplayName("ADD 장소 리뷰 Bonus 횟득X content X 1점 부여")
    @Transactional
    @Rollback
    public void Add() throws Exception {
        //given
        EventDto oldEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        EventDto newEventDto = eventDto(reviewId2, "ADD", "", attachePhotoIds2, userRes2);

        //when
        reviewEventService.events(oldEventDto);
        EventRes events = reviewEventService.events(newEventDto);

        //then
        EventRes eventRes = new EventRes(1, 0, newEventDto);

        Assertions.assertThat(events.getReviewPoint()).isEqualTo(eventRes.getReviewPoint());
        Assertions.assertThat(events.getBonusPoint()).isEqualTo(eventRes.getBonusPoint());
        Assertions.assertThat(events.getEventReq().getReviewId()).isEqualTo(eventRes.getEventReq().getReviewId());
    }

    @Test
    @DisplayName("MOD Content O -> Content X : -1점 부여")
    @Transactional
    @Rollback
    public void MODContentX() throws Exception {
        //given
        EventDto oldEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        EventDto newEventDto = eventDto(reviewId1, "MOD", "", attachePhotoIds1, userRes1);

        //when
        reviewEventService.events(oldEventDto);
        EventRes events = reviewEventService.events(newEventDto);

        //then
        EventRes eventRes = new EventRes(-1, 0, newEventDto);

        Assertions.assertThat(events.getReviewPoint()).isEqualTo(eventRes.getReviewPoint());
        Assertions.assertThat(events.getBonusPoint()).isEqualTo(eventRes.getBonusPoint());
        Assertions.assertThat(events.getEventReq().getReviewId()).isEqualTo(eventRes.getEventReq().getReviewId());
    }

    @Test
    @DisplayName("MOD photo O -> photo X : -1점 부여")
    @Transactional
    @Rollback
    public void MODPhotoX() throws Exception {
        //given
        EventDto oldEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        EventDto newEventDto = eventDto(reviewId1, "MOD", "Add Hello", new ArrayList<>(), userRes1);

        //when
        reviewEventService.events(oldEventDto);
        EventRes events = reviewEventService.events(newEventDto);

        //then
        EventRes eventRes = new EventRes(-1, 0, newEventDto);

        Assertions.assertThat(events.getReviewPoint()).isEqualTo(eventRes.getReviewPoint());
        Assertions.assertThat(events.getBonusPoint()).isEqualTo(eventRes.getBonusPoint());
        Assertions.assertThat(events.getEventReq().getReviewId()).isEqualTo(eventRes.getEventReq().getReviewId());
    }

    @Test
    @DisplayName("삭제시 얻음 점수 전부 삭제")
    @Transactional
    @Rollback
    public void DELETE() throws Exception {
        //given
        EventDto oldEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        EventDto newEventDto = eventDto(reviewId1, "DELETE", "Add Hello", new ArrayList<>(), userRes1);

        //when
        EventRes events = reviewEventService.events(oldEventDto);
        EventRes deleteEvents = reviewEventService.events(newEventDto);

        //then
        EventRes eventRes = new EventRes(-2, -1, newEventDto);

        Assertions.assertThat(deleteEvents.getReviewPoint()).isEqualTo(eventRes.getReviewPoint());
        Assertions.assertThat(deleteEvents.getBonusPoint()).isEqualTo(eventRes.getBonusPoint());
        Assertions.assertThat(deleteEvents.getEventReq().getReviewId()).isEqualTo(eventRes.getEventReq().getReviewId());

    }

    @Test
    @DisplayName("A가 첫 리뷰가 삭제 후, B가 두번재 리뷰를 남기면 bonus O")
    public void getBonusPointWhenDeleteFirstAfterSecond() throws Exception {
        //given
        EventDto firstEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        EventDto secondEventDto = eventDto(reviewId2, "ADD", "Add Hello", attachePhotoIds2, userRes2);
        EventDto firDelEventDto = eventDto(reviewId1, "DELETE", "Delete Hello", new ArrayList<>(), userRes1);

        //when
        EventRes firEvents = reviewEventService.events(firstEventDto);
        EventRes firDeleteEvents = reviewEventService.events(firDelEventDto);
        EventRes secEvents = reviewEventService.events(secondEventDto);

        //then
        EventRes secEventRes = new EventRes(2, 1, secondEventDto);
        EventRes firEventRes = new EventRes(2, 1, firstEventDto);

        Assertions.assertThat(firEvents.getReviewPoint()).isEqualTo(firEventRes.getReviewPoint());
        Assertions.assertThat(firEvents.getBonusPoint()).isEqualTo(firEventRes.getBonusPoint());
        Assertions.assertThat(firEvents.getEventReq().getUserId()).isEqualTo(firEventRes.getEventReq().getUserId());

        Assertions.assertThat(secEvents.getReviewPoint()).isEqualTo(secEventRes.getReviewPoint());
        Assertions.assertThat(secEvents.getBonusPoint()).isEqualTo(secEventRes.getBonusPoint());
        Assertions.assertThat(secEvents.getEventReq().getUserId()).isEqualTo(secEventRes.getEventReq().getUserId());

    }

    @Test
    @DisplayName("A가 첫 리뷰가 삭제하기전, B가 두번재 리뷰를 남기면 bonus X")
    public void WithoutBonusPointWhenDeleteFirstBeforeSecond() throws Exception {
        //given
        EventDto firstEventDto = eventDto(reviewId1, "ADD", "Add Hello", attachePhotoIds1, userRes1);
        EventDto secondEventDto = eventDto(reviewId2, "ADD", "Add Hello", attachePhotoIds2, userRes2);
        EventDto firDelEventDto = eventDto(reviewId1, "DELETE", "Delete Hello", new ArrayList<>(), userRes1);

        //when
        EventRes firEvents = reviewEventService.events(firstEventDto);
        EventRes secEvents = reviewEventService.events(secondEventDto);
        EventRes firDeleteEvents = reviewEventService.events(firDelEventDto);

        //then
        EventRes firDelEventRes = new EventRes(-2, -1, firDelEventDto);
        EventRes secEventRes = new EventRes(2, 0, secondEventDto);

        Assertions.assertThat(firDeleteEvents.getReviewPoint()).isEqualTo(firDelEventRes.getReviewPoint());
        Assertions.assertThat(firDeleteEvents.getBonusPoint()).isEqualTo(firDelEventRes.getBonusPoint());
        Assertions.assertThat(firDeleteEvents.getEventReq().getUserId()).isEqualTo(firDelEventRes.getEventReq().getUserId());

        Assertions.assertThat(secEvents.getReviewPoint()).isEqualTo(secEventRes.getReviewPoint());
        Assertions.assertThat(secEvents.getBonusPoint()).isEqualTo(secEventRes.getBonusPoint());
        Assertions.assertThat(secEvents.getEventReq().getUserId()).isEqualTo(secEventRes.getEventReq().getUserId());
    }

}
