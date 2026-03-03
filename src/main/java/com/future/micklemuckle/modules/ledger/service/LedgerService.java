package com.future.micklemuckle.modules.ledger.service;

import com.future.micklemuckle.modules.ledger.dto.LedgerEntryDto;
import com.future.micklemuckle.modules.ledger.repository.LedgerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<LedgerEntryDto> getLedgerEntryByEntryType(String entryType) {
        return ledgerRepository.findLedgerEntryByEntryType(entryType)
                .stream()
                .map(LedgerEntryDto::fromEntity)
                .toList();
    }

    public List<LedgerEntryDto> getLedgerEntryByEntryDate(String year, String month) {
        LocalDate startDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
        LocalDate endDate = startDate.plusMonths(1);
        return ledgerRepository.findLedgerEntriesByEntryDate(startDate, endDate)
                .stream().map(LedgerEntryDto::fromEntity).toList();
    }
}
