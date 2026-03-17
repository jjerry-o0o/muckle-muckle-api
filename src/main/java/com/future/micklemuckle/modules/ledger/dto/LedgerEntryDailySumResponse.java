package com.future.micklemuckle.modules.ledger.dto;

import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;

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
        int amount
) {
    public static LedgerEntryDailySumResponse fromEntity(LedgerEntry entity) {
            return new LedgerEntryDailySumResponse(
                entity.getEntryDate(),
                entity.getEntryType(),
                entity.getAmount()
        );
    }
}
