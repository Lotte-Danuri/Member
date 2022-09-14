package com.lotte.danuri.member.common.exception.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid Parameter Included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource Not Exists")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
