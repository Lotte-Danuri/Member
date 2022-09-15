package com.lotte.danuri.member.common.exception.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements ErrorCode {

    DUPLICATED_STORE_NAME(HttpStatus.BAD_REQUEST, "Duplicated Store Name"),
    NO_STORE_EXISTS(HttpStatus.NOT_FOUND, "Store is not existed")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
