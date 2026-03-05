package com.future.micklemuckle.modules.ledger.service;

import com.future.micklemuckle.modules.categories.entity.Category;
import com.future.micklemuckle.modules.ledger.dto.LedgerEntryDto;
import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
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

    public LedgerEntryDto getLedgerEntryByEntryId(String id) {
        return LedgerEntryDto.fromEntity(ledgerRepository.findLedgerEntryByEntryId(id));
    }

    public void saveLedgerEntry(LedgerEntryDto reqDto) {

        Category category = null;
        if (reqDto.getCategoryId() != null) {
//            category = getLedgerEntryByEntryId(reqDto.getCategoryId());
        }

    }

}
