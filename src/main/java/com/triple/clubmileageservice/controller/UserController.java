package com.triple.clubmileageservice.controller;

import com.triple.clubmileageservice.service.domain.UserService;
import com.triple.clubmileageservice.reqres.UserReq;
import com.triple.clubmileageservice.reqres.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public UserRes createUser(@RequestBody UserReq userReq) {
        return userService.createUser(userReq.getUserEmail());
    }

    @GetMapping("/findAllUser")
    public List<UserRes> findAllUser() {
        return userService.findAllUser();
    }
}
