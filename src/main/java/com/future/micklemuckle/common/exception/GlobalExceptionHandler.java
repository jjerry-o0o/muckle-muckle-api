package com.future.micklemuckle.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler
 *
 * @author : future
 * @date : 2026-03-27
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ErrorResponse response = new ErrorResponse(
                errorCode.getCode(), errorCode.getMessage()
        );

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
}
