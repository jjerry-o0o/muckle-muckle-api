package com.future.micklemuckle.modules.ledger.dto;

import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;

import java.time.LocalDate;

/**
 * LedgerEntryResDto
 *
 * @author : future
 * @date : 2026-03-06
 */
public record LedgerEntryDetailResponse(
        Long entryId,
        LocalDate entryDate,
        String entryType,
        int amount,
        String title,
        String memo,
        Long categoryId,
        Long paymentId
) {
    public static LedgerEntryDetailResponse fromEntity(LedgerEntry entity) {
        return new LedgerEntryDetailResponse(
                entity.getId(),
                entity.getEntryDate(),
                entity.getEntryType(),
                entity.getAmount(),
                entity.getTitle(),
                entity.getMemo(),
                entity.getCategory().getId(),
                entity.getPayment().getId()

        );
    }
}
