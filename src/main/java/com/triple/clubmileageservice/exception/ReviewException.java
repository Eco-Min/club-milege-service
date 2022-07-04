package com.triple.clubmileageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class ReviewException extends RuntimeException {
    public ReviewException(String reviewId, boolean exist) {
        super(reviewId + " review 가 등록 되었거나 삭제된 review 입니다.");
    }
}
