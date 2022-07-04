package com.triple.clubmileageservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.clubmileageservice.dto.EventDto;
import com.triple.clubmileageservice.reqres.EventReq;
import com.triple.clubmileageservice.reqres.EventRes;
import com.triple.clubmileageservice.service.ReviewEventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.triple.clubmileageservice.ApiDocumentUtils.getDocumentRequest;
import static com.triple.clubmileageservice.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class EventControllerTest {

    @Autowired
    private ReviewEventService reviewEventService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Events - ActionType : ADD")
    public void ADD() throws Exception {
        //given
        /*EventReq eventReq = new EventReq("REVIEW", "ADD",
                "240a0658-dc5f-4878-9381-ebb7b2667715", "Add Hello",
                List.of("e4d1a64e-a531-46de-88d0-ff0ed70c0b15", "afb0cef2-851d-4a50-bb07-9cc15cbdc315"),
                "c0888352-c425-4124-b5f0-14ff2c51be0f", "06519360-441d-4e1d-b304-8a133e049485");
        EventDto eventDto = EventDto.createEventDto(eventReq);
        EventRes eventRes = new EventRes(2, 1, eventDto);

        given(reviewEventService.events(eventDto)).willReturn(eventRes);

        String content = objectMapper.writeValueAsString(eventReq);

        //when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/events")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andDo(document("index",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("type").type(JsonFieldType.NUMBER).description("type"),
                                fieldWithPath("action").type(JsonFieldType.NUMBER).description("action"),
                                fieldWithPath("reviewId").type(JsonFieldType.NUMBER).description("reviewId"),
                                fieldWithPath("content").type(JsonFieldType.NUMBER).description("content"),
                                fieldWithPath("attachedPhotoIds").type(JsonFieldType.OBJECT).description("attachedPhotoIds"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("userId"),
                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("placeId")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("bonusPoint"),
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("reviewPoint"),
                                fieldWithPath("id").type(JsonFieldType.OBJECT).description("eventDto")
                        )));*/

    }
}