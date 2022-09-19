package com.lotte.danuri.member.common.exception.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CouponErrorCode implements ErrorCode {

    NO_COUPONS_EXISTS(HttpStatus.NOT_FOUND, "Coupons are not existed");
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
