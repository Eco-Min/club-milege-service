package com.triple.clubmileageservice.reqres;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class UserReq {
    @Email
    private String userEmail;
}
