package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.ledger.dto.CreateLedgerEntryReqDto;
import com.future.micklemuckle.modules.ledger.dto.LedgerEntryResDto;
import com.future.micklemuckle.modules.ledger.dto.UpdateLedgerEntryReqDto;
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
    public LedgerEntryResDto getLedgerEntry(@PathVariable Long id){
        return ledgerService.getLedgerEntryByEntryId(id);
    }

    @PostMapping("/")
    public Long saveLedgerEntry(@RequestBody CreateLedgerEntryReqDto reqDto){
        return ledgerService.saveLedgerEntry(reqDto);
    }

    @PatchMapping("/{id}")
    public Long updateLedgerEntry(@PathVariable Long id, @RequestBody UpdateLedgerEntryReqDto reqDto){
        return ledgerService.updateLedgerEntry(id, reqDto);
    }
}
