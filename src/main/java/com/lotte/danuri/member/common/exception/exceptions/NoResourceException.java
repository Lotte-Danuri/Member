package com.lotte.danuri.member.common.exception.exceptions;

import com.lotte.danuri.member.common.exception.codes.ErrorCode;

public class NoResourceException extends CustomException {

    public NoResourceException(String message, ErrorCode errorCode) {
        super(message, errorCode);

    }

}
