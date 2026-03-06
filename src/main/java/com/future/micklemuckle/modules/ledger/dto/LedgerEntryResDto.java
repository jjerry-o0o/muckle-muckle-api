package com.future.micklemuckle.modules.ledger.dto;

import com.future.micklemuckle.modules.categories.entity.Category;
import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import com.future.micklemuckle.modules.payment.entity.PaymentMethod;

import java.time.LocalDate;

/**
 * LedgerEntryResDto
 *
 * @author : future
 * @date : 2026-03-06
 */
public record LedgerEntryResDto(
        Long entryId,
        LocalDate entryDate,
        String entryType,
        int amount,
        String title,
        String memo,
        Long categoryId,
        Long paymentId
) {
    public static LedgerEntryResDto fromEntity(LedgerEntry entity) {
        return new LedgerEntryResDto(
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
