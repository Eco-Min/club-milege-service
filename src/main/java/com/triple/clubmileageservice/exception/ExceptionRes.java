package com.triple.clubmileageservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionRes {
    private Date timestamp;
    private String message;
    private String details;
}
