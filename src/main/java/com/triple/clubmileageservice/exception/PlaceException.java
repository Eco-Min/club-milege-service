package com.triple.clubmileageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class PlaceException extends RuntimeException  {
    public PlaceException(String userId) {
        super(userId + " place 가 존재하지 않습니다.");
    }
}
