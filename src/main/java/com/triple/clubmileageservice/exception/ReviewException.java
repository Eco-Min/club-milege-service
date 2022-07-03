package com.triple.clubmileageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class ReviewException extends RuntimeException {
    public ReviewException(String reviewId, boolean exist) {
        super(reviewId + " review 등록중 review 가 존재합니다.");
    }
}
