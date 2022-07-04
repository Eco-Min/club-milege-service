package com.triple.clubmileageservice.reqres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserPointRes {
    private String userId;
    private Integer point;
}
