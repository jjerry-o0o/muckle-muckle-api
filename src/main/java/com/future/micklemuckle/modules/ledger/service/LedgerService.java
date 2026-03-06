package com.future.micklemuckle.modules.ledger.service;

import com.future.micklemuckle.common.exception.NotFoundException;
import com.future.micklemuckle.modules.categories.entity.Category;
import com.future.micklemuckle.modules.categories.repository.CategoriesRepository;
import com.future.micklemuckle.modules.ledger.dto.CreateLedgerEntryReqDto;
import com.future.micklemuckle.modules.ledger.dto.LedgerEntryDto;
import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import com.future.micklemuckle.modules.ledger.repository.LedgerRepository;
import com.future.micklemuckle.modules.payment.entity.PaymentMethod;
import com.future.micklemuckle.modules.payment.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * LedgerService
 *
 * @author : future
 * @date : 2026-03-03
 */
@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;
    private final CategoriesRepository categoriesRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public LedgerEntryDto getLedgerEntryByEntryId(Long id) {
        return LedgerEntryDto.fromEntity(ledgerRepository.findLedgerEntryByEntryId(id));
    }

    public String saveLedgerEntry(CreateLedgerEntryReqDto reqDto) {

        Category category = null;
        if (reqDto.getCategoryId() != null) {
            category = categoriesRepository.findById(reqDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("category not found"));
        }

        PaymentMethod payment = null;
        if (reqDto.getPaymentId() != null) {
            payment = paymentMethodRepository.findById(reqDto.getPaymentId())
                    .orElseThrow(() -> new NotFoundException("payment method not found"));
        }

        LedgerEntry ledgerEntry = LedgerEntry.builder()
                .entryDate(reqDto.getEntryDate())
                .entryType(reqDto.getEntryType())
                .amount(reqDto.getAmount())
                .title(reqDto.getTitle())
                .memo(reqDto.getMemo())
                .category(category)
                .payment(payment)
                .build();
        ledgerRepository.save(ledgerEntry);
        return String.valueOf(ledgerEntry.getId());
    }

}
