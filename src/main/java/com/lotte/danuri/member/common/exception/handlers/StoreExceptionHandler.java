package com.lotte.danuri.member.common.exception.handlers;

import com.lotte.danuri.member.common.exception.ErrorResponse;
import com.lotte.danuri.member.common.exception.exceptions.DuplicatedStoreNameException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class StoreExceptionHandler {

    @ExceptionHandler(NoStoreException.class)
    public ResponseEntity<?> handleNoStoreException(NoStoreException e) {
        log.error("handleNoStoreException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(DuplicatedStoreNameException.class)
    public ResponseEntity<?> handleDuplicatedStoreNameException(DuplicatedStoreNameException e) {
        log.error("handleDuplicatedStoreNameException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }
}
