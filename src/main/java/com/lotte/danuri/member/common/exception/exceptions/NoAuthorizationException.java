package com.lotte.danuri.member.common.exception.exceptions;

import com.lotte.danuri.member.common.exception.codes.ErrorCode;
import lombok.Getter;

@Getter
public class NoAuthorizationException extends CustomException {

    public NoAuthorizationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
