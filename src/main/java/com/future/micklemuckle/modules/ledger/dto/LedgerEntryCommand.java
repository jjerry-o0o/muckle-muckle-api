package com.future.micklemuckle.modules.ledger.dto;

import com.future.micklemuckle.modules.categories.entity.Category;
import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import com.future.micklemuckle.modules.paymentMethod.entity.PaymentMethod;

import java.time.LocalDate;

public record LedgerEntryCommand(
        Long entryId,
        LocalDate entryDate,
        String entryType,
        int amount,
        String title,
        String memo,
        Category category,
        PaymentMethod payment
) {
    public static LedgerEntryCommand fromEntity(LedgerEntry entity) {
        return new LedgerEntryCommand(
                entity.getId(),
                entity.getEntryDate(),
                entity.getEntryType(),
                entity.getAmount(),
                entity.getTitle(),
                entity.getMemo(),
                entity.getCategory(),
                entity.getPayment()

        );
    }
}
