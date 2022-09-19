package com.lotte.danuri.member.common.exception.exceptions;

import com.lotte.danuri.member.common.exception.codes.ErrorCode;

public class NoMyCouponException extends CustomException {

    public NoMyCouponException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
