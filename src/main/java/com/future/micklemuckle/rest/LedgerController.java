package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.ledger.dto.*;
import com.future.micklemuckle.modules.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public LedgerEntryDetailResponse getLedgerEntry(@PathVariable Long id){
        return ledgerService.getLedgerEntryByEntryId(id);
    }

    @PostMapping("/")
    public Long saveLedgerEntry(@RequestBody CreateLedgerEntryRequest reqDto){
        return ledgerService.saveLedgerEntry(reqDto);
    }

    @PatchMapping("/{id}")
    public Long updateLedgerEntry(@PathVariable Long id, @RequestBody UpdateLedgerEntryRequest reqDto){
        return ledgerService.updateLedgerEntry(id, reqDto);
    }

    @GetMapping("/month/{targetYm}")
    public List<LedgerEntrySummaryResponse> getLedgerEntriesByMonth(@PathVariable String targetYm){
        return ledgerService.getLedgerEntriesByMonth(targetYm);
    }

    @GetMapping("/List/{pageNum}")
    public Slice<LedgerEntryDetailResponse> getLedgerEntriesByPagination(@PathVariable int pageNum){
        return ledgerService.getLedgerEntriesByPagination(pageNum);
    }

    @GetMapping("/month/sum/{targetYm}")
    public List<LedgerEntryDailySumResponse> getLedgerEntriesDailySum(@PathVariable String targetYm){
        return ledgerService.getLedgerEntriesDailySum(targetYm);
    }
}
