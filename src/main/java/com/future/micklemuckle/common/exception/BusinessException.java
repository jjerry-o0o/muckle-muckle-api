package com.future.micklemuckle.common.exception;

import lombok.Getter;

/**
 * BusinessException
 *
 * @author : future
 * @date : 2026-03-27
 */
@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
