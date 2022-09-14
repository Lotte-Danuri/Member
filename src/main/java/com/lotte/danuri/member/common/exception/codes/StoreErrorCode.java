package com.lotte.danuri.member.common.exception.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements ErrorCode {

    DUPLICATED_STORE_NAME(HttpStatus.BAD_REQUEST, "Duplicated Store Name")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
