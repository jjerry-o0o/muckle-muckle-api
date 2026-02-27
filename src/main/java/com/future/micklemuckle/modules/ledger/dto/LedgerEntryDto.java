package com.future.micklemuckle.modules.ledger.dto;

import com.future.micklemuckle.modules.ledger.entity.LedgerCategory;
import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import com.future.micklemuckle.modules.ledger.entity.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LedgerEntryDto {

    private final Long id;
    private final LocalDate entryDate;
    private final String entryType;
    private final int amount;
    private final String title;
    private final String memo;

    private Long categoryId;
    private Long paymentId;
//    private LedgerCategory category;
//    private PaymentMethod payment;

    public static LedgerEntryDto fromEntity(LedgerEntry entity) {
        LedgerEntryDto dto = LedgerEntryDto.builder()
                .id(entity.getId())
                .entryDate(entity.getEntry_date())
                .entryType(entity.getEntry_type())
                .amount(entity.getAmount())
                .title(entity.getTitle())
                .memo(entity.getMemo())
                .categoryId(entity.getCategory() != null ? entity.getCategory().getId() : null)
                .paymentId(entity.getPayment() != null ? entity.getPayment().getId() : null)
                .build();
    }
}
