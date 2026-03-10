package com.future.micklemuckle.modules.ledger.dto;

import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;

import java.time.LocalDate;

/**
 * LedgerEntrySummaryResponse
 *
 * @author : future
 * @date : 2026-03-10
 */
public record LedgerEntrySummaryResponse (
        Long entryId,
        LocalDate entryDate,
        String entryType,
        int amount,
        String title
) {
    public static LedgerEntrySummaryResponse fromEntity(LedgerEntry entity) {
            return new LedgerEntrySummaryResponse(
                entity.getId(),
                entity.getEntryDate(),
                entity.getEntryType(),
                entity.getAmount(),
                entity.getTitle()
        );
    }
}
