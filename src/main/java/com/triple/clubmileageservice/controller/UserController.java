package com.triple.clubmileageservice.controller;

import com.triple.clubmileageservice.service.UserService;
import com.triple.clubmileageservice.vo.ResponseUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseUserVo createUser(@RequestBody String userEmail) {
        String emailRegex = "^(.+)@(.+)$";
        boolean matcherEmail = Pattern.compile(emailRegex).matcher(userEmail).find();
        if (matcherEmail) {
            return userService.createUser(userEmail);
        }
        return null;
    }

    @GetMapping("/findAllUser")
    public List<ResponseUserVo> findAllUser() {
        return userService.findAllUser();
    }
}
