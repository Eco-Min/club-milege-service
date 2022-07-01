package com.triple.clubmileageservice.vo;

public class ResponseUserVo {
    private String userId;
    private String userEmail;

    public ResponseUserVo(String id, String email) {
        this.userId = id;
        this.userEmail = email;
    }
}
