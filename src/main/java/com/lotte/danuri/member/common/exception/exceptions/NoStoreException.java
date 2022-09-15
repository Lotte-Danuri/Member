package com.lotte.danuri.member.common.exception.exceptions;

import com.lotte.danuri.member.common.exception.codes.ErrorCode;

public class NoStoreException extends CustomException {

    public NoStoreException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
