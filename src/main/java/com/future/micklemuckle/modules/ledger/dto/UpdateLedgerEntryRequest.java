package com.future.micklemuckle.modules.ledger.dto;

import lombok.Getter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

/**
 * UpdateLedgerEntryReqDto
 *
 * @author : future
 * @date : 2026-03-06
 */
@Getter
public class UpdateLedgerEntryRequest {

    private LocalDate entryDate;
    private String entryType;
    private int amount;
    private String title;
    private JsonNullable<String> memo = JsonNullable.undefined();
    private Long categoryId;
    private Long paymentId;
}
