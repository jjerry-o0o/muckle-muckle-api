package com.future.micklemuckle.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * ErrorCode
 *
 * @author : future
 * @date : 2026-03-27
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    LEDGER_NOT_FOUND(HttpStatus.NOT_FOUND, "L001", "요청하신 수입/지출 항목을 찾을 수 없습니다."),
    LEDGER_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "L002", "수입/지출 목록 조회에 실패했습니다."),
    INVALID_LEDGER_DATE(HttpStatus.BAD_REQUEST, "L003", "날짜 형식이 올바르지 않습니다."),

    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "C001", "요청하신 카테고리 항목을 찾을 수 없습니다."),
    CATEGORY_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "C002", "카테고리 목록 조회에 실패했습니다."),

    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "요청하신 결제수단 항목을 찾을 수 없습니다."),
    PAYMENT_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "P002", "결제수단 목록 조회에 실패했습니다.")

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
