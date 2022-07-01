package com.triple.clubmileageservice.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDto {
    private String userId;
    private String userEmail;

    private UserDto(String userId, String userEmail) {
        this.userId = userId;
        this.userEmail = userEmail;
    }

    public static UserDto createUserDto(String userEmail) {
        return new UserDto(UUID.randomUUID().toString(), userEmail);
    }
}
