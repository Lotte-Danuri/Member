package com.lotte.danuri.member.common.exception.handlers;

import com.lotte.danuri.member.common.exception.ErrorResponse;
import com.lotte.danuri.member.common.exception.exceptions.NoMyCouponException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyCouponExceptionHandler {

    @ExceptionHandler(NoMyCouponException.class)
    public ResponseEntity<?> handleNoMyCouponException(NoMyCouponException e) {
        log.error("handleNoMyCouponException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }
}
