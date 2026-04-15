package com.future.micklemuckle.modules.ledger.service;

import com.future.micklemuckle.common.exception.ErrorCode;
import com.future.micklemuckle.common.exception.NotFoundException;
import com.future.micklemuckle.modules.categories.entity.Category;
import com.future.micklemuckle.modules.categories.repository.CategoriesRepository;
import com.future.micklemuckle.modules.ledger.dto.*;
import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import com.future.micklemuckle.modules.ledger.repository.LedgerRepository;
import com.future.micklemuckle.modules.paymentMethod.entity.PaymentMethod;
import com.future.micklemuckle.modules.paymentMethod.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

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

    public LedgerEntryDetailResponse getLedgerEntryByEntryId(Long id) {
        LedgerEntry ledgerEntry = ledgerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.LEDGER_NOT_FOUND));
        return LedgerEntryDetailResponse.fromEntity(ledgerEntry);
    }

    @Transactional
    public Long saveLedgerEntry(CreateLedgerEntryRequest reqDto) {

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
    public Long updateLedgerEntry(Long id, UpdateLedgerEntryRequest req) {

        LedgerEntry entry = ledgerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.LEDGER_NOT_FOUND));
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
                    .orElseThrow(() -> new NotFoundException(ErrorCode.CATEGORY_NOT_FOUND));
        }
        return category;
    }

    private PaymentMethod getPaymentMethod(Long id) {
        PaymentMethod payment = null;
        if (id != null) {
            payment = paymentMethodRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
        }
        return payment;
    }

    public List<LedgerEntrySummaryResponse> getLedgerEntriesByMonth(String targetYm) {
        YearMonth ym = YearMonth.parse(targetYm);

        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        return ledgerRepository.findByEntryDateBetween(startDate, endDate)
                .stream()
                .map(LedgerEntrySummaryResponse::fromEntity)
                .toList();
    }

    public Slice<LedgerEntryDetailResponse> getLedgerEntriesByPagination(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 20, Sort.by(Sort.Order.desc("entryDate"), Sort.Order.desc("entryType"), Sort.Order.desc("title")));
                return ledgerRepository.findWithSlice(pageable)
                        .map(LedgerEntryDetailResponse::fromEntity);
    }

    public List<LedgerEntryDailySumResponse> getLedgerEntriesDailySum(String targetYm) {
        YearMonth ym = YearMonth.parse(targetYm);

        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        return ledgerRepository.findAmountSumByDateAndType(startDate, endDate)
                .stream()
                .map(result -> new LedgerEntryDailySumResponse(
                        result.getEntryDate(),
                        result.getEntryType(),
                        result.getAmount()
                ))
                .toList();
    }

    @Transactional
    public void deleteLedgerEntry(Long id) {
        LedgerEntry entry = ledgerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.LEDGER_NOT_FOUND));

        ledgerRepository.delete(entry);
    }

    public List<LedgerEntryDetailResponse> getLedgerEntriesByDate(String targetDate) {
        LocalDate date = LocalDate.parse(targetDate);
        return ledgerRepository.findByEntryDate(date)
                .stream()
                .map(LedgerEntryDetailResponse::fromEntity)
                .toList();
    }
}
