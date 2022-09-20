package com.lotte.danuri.member.common.exception.exceptions;

import com.lotte.danuri.member.common.exception.codes.ErrorCode;

public class SellerStoreExistedException extends CustomException {

    public SellerStoreExistedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
