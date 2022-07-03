package com.triple.clubmileageservice.reqres;

import lombok.Getter;

@Getter
public class UserRes {
    private final String userId;
    private final String userEmail;

    public UserRes(String id, String email) {
        this.userId = id;
        this.userEmail = email;
    }
}
