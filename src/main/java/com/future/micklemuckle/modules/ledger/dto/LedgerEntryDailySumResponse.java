package com.future.micklemuckle.modules.ledger.dto;

import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * LedgerEntryDailySumResponse
 *
 * @author : future
 * @date : 2026-03-17
 */
public record LedgerEntryDailySumResponse(
        LocalDate entryDate,
        String entryType,
        BigDecimal amount
) {
    public static LedgerEntryDailySumResponse fromEntity(LedgerEntry entity) {
            return new LedgerEntryDailySumResponse(
                entity.getEntryDate(),
                entity.getEntryType(),
                entity.getAmount()
        );
    }
}
