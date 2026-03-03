package com.future.micklemuckle.modules.ledger.service;

import com.future.micklemuckle.modules.ledger.dto.LedgerEntryDto;
import com.future.micklemuckle.modules.ledger.repository.LedgerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<LedgerEntryDto> getLedgerEntryList(String entryType) {
        return ledgerRepository.findLedgerEntryByEntryType(entryType)
                .stream()
                .map(LedgerEntryDto::fromEntity)
                .toList();
    }
}
