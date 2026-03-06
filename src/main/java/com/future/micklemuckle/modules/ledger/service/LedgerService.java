package com.future.micklemuckle.modules.ledger.service;

import com.future.micklemuckle.common.exception.NotFoundException;
import com.future.micklemuckle.modules.categories.entity.Category;
import com.future.micklemuckle.modules.categories.repository.CategoriesRepository;
import com.future.micklemuckle.modules.ledger.dto.CreateLedgerEntryReqDto;
import com.future.micklemuckle.modules.ledger.dto.LedgerEntryResDto;
import com.future.micklemuckle.modules.ledger.dto.UpdateLedgerEntryReqDto;
import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import com.future.micklemuckle.modules.ledger.repository.LedgerRepository;
import com.future.micklemuckle.modules.payment.entity.PaymentMethod;
import com.future.micklemuckle.modules.payment.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public LedgerEntryResDto getLedgerEntryByEntryId(Long id) {
        LedgerEntry ledgerEntry = ledgerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LedgerEntry not found"));
        return LedgerEntryResDto.fromEntity(ledgerEntry);
    }

    @Transactional
    public Long saveLedgerEntry(CreateLedgerEntryReqDto reqDto) {

        Category category = getCategory(reqDto.getCategoryId());
        PaymentMethod payment = getPaymentMethod(reqDto.getPaymentId());

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
        return ledgerEntry.getId();
    }

    @Transactional
    public Long updateLedgerEntry(Long id, UpdateLedgerEntryReqDto req) {

        LedgerEntry entry = ledgerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LedgerEntry not found"));
        Category category = getCategory(req.getCategoryId());
        PaymentMethod payment = getPaymentMethod(req.getPaymentId());

        if (req.getMemo().isPresent()) {
            entry.changeMemo(req.getMemo().get());
        }

        entry.update(req.getEntryDate(), req.getEntryType(), req.getAmount(), req.getTitle(), category, payment);

        return entry.getId();
    }

    private Category getCategory(Long id) {
        Category category = null;
        if (id != null) {
            category = categoriesRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("category not found"));
        }
        return category;
    }

    private PaymentMethod getPaymentMethod(Long id) {
        PaymentMethod payment = null;
        if (id != null) {
            payment = paymentMethodRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("payment method not found"));
        }
        return payment;
    }

}
