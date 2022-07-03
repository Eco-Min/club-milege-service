package com.triple.clubmileageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserException extends RuntimeException {
    public UserException(String userId, String email) {
        super(email + " : " + userId + "가 존재하지 않습니다.");
    }

    public UserException(String userId) {
        super(userId + " user 가 존재하지 않습니다.");
    }


}
