package com.future.micklemuckle.common.exception;

/**
 * NotFoundException
 *
 * @author : future
 * @date : 2026-03-06
 */
public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
