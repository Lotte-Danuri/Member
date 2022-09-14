package com.lotte.danuri.member.common.exception.hadlers;

import com.lotte.danuri.member.common.exception.ErrorResponse;
import com.lotte.danuri.member.common.exception.exceptions.NoResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(NoResourceException.class)
    public ResponseEntity<?> noResourceException(NoResourceException e) {
        log.error("noResourceException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }
}
