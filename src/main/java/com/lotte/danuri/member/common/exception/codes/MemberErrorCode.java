package com.lotte.danuri.member.common.exception.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    NO_AUTHORIZED_SELLER(HttpStatus.FORBIDDEN, "Seller is unauthorized"),
    NO_MEMBER_EXISTS(HttpStatus.NOT_FOUND, "Member is not Existed"),
    SELLER_STORE_EXISTS(HttpStatus.FORBIDDEN, "Seller still has store, can't withdraw from membership")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
