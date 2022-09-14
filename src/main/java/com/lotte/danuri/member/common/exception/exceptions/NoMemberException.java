package com.lotte.danuri.member.common.exception.exceptions;

import com.lotte.danuri.member.common.exception.codes.ErrorCode;
import lombok.Getter;

@Getter
public class NoMemberException extends CustomException {

    public NoMemberException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
