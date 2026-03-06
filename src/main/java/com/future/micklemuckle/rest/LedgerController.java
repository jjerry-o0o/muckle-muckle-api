package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.ledger.dto.CreateLedgerEntryReqDto;
import com.future.micklemuckle.modules.ledger.dto.LedgerEntryDto;
import com.future.micklemuckle.modules.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public LedgerEntryDto getLedgerEntry(@PathVariable String id){
        return ledgerService.getLedgerEntryByEntryId(Long.parseLong(id));
    }

    @PostMapping("/")
    public String saveLedgerEntry(@RequestBody CreateLedgerEntryReqDto ledgerEntryDto){
        return ledgerService.saveLedgerEntry(ledgerEntryDto);
    }

}
