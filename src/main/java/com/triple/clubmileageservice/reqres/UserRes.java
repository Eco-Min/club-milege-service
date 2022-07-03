package com.triple.clubmileageservice.reqres;

import lombok.Getter;

@Getter
public class UserRes {
    private String userId;
    private String userEmail;

    public UserRes(String id, String email) {
        this.userId = id;
        this.userEmail = email;
    }
}
