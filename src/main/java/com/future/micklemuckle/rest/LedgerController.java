package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.ledger.dto.*;
import com.future.micklemuckle.modules.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LedgerEntryDetailResponse> getLedgerEntry(@PathVariable Long id){
        LedgerEntryDetailResponse response = ledgerService.getLedgerEntryByEntryId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Long> saveLedgerEntry(@RequestBody CreateLedgerEntryRequest reqDto){
        Long response = ledgerService.saveLedgerEntry(reqDto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateLedgerEntry(@PathVariable Long id, @RequestBody UpdateLedgerEntryRequest reqDto){
        Long response = ledgerService.updateLedgerEntry(id, reqDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/month/{targetYm}")
    public ResponseEntity<List<LedgerEntrySummaryResponse>> getLedgerEntriesByMonth(@PathVariable String targetYm){
        List<LedgerEntrySummaryResponse> response =  ledgerService.getLedgerEntriesByMonth(targetYm);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/List/{pageNum}")
    public ResponseEntity<Slice<LedgerEntryDetailResponse>> getLedgerEntriesByPagination(@PathVariable int pageNum){
        Slice<LedgerEntryDetailResponse> response = ledgerService.getLedgerEntriesByPagination(pageNum);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/month/sum/{targetYm}")
    public ResponseEntity<List<LedgerEntryDailySumResponse>> getLedgerEntriesDailySum(@PathVariable String targetYm){
        List<LedgerEntryDailySumResponse> response =  ledgerService.getLedgerEntriesDailySum(targetYm);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLedgerEntry(@PathVariable Long id) {
        ledgerService.deleteLedgerEntry(id);
        return ResponseEntity.noContent().build();
    }
}
