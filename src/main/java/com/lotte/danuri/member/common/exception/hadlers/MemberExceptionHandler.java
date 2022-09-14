package com.lotte.danuri.member.common.exception.hadlers;

import com.lotte.danuri.member.common.exception.ErrorResponse;
import com.lotte.danuri.member.common.exception.exceptions.NoAuthorizationException;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(NoAuthorizationException.class)
    public ResponseEntity<?> handleSellerNoAuthorization(NoAuthorizationException e) {
        log.error("handleSellerNoAuthorization", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(NoMemberException.class)
    public ResponseEntity<?> handleNoMemberException(NoMemberException e) {
        log.error("handleNoMemberException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }
}
