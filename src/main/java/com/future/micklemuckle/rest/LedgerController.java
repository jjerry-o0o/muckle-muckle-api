package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.ledger.dto.LedgerEntryDto;
import com.future.micklemuckle.modules.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 수입/지출 항목 Controller
 *
 * @author : future
 * @date : 2026-02-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    @GetMapping(value = "/")
    public List<LedgerEntryDto> getLedgerEntries(@RequestParam(required = false) String entryType, @RequestParam(required = false) String year, @RequestParam(required = false) String month) {
        if (entryType == null) {
            return ledgerService.getLedgerEntryByEntryDate(year, month);
        } else {
            return ledgerService.getLedgerEntryByEntryType(entryType);
        }
    }

}
