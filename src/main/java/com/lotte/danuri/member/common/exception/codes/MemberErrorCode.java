package com.lotte.danuri.member.common.exception.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    NO_AUTHORIZED_SELLER(HttpStatus.FORBIDDEN, "Seller is unauthorized"),
    NO_MEMBER_EXISTS(HttpStatus.NOT_FOUND, "Member is not Existed")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
