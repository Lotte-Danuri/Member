package com.lotte.danuri.member.common.exception;

import com.lotte.danuri.member.common.exception.codes.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
