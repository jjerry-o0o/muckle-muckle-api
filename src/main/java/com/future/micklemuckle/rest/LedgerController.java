package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.ledger.dto.*;
import com.future.micklemuckle.modules.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/month/{targetYm}")
    public ResponseEntity<List<LedgerEntrySummaryResponse>> getLedgerEntriesByMonth(@AuthenticationPrincipal Long userId, @PathVariable String targetYm){
        List<LedgerEntrySummaryResponse> response =  ledgerService.getLedgerEntriesByMonth(userId, targetYm);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/List/{pageNum}")
    public ResponseEntity<Slice<LedgerEntryDetailResponse>> getLedgerEntriesByPagination(@AuthenticationPrincipal Long userId, @PathVariable int pageNum){
        Slice<LedgerEntryDetailResponse> response = ledgerService.getLedgerEntriesByPagination(userId, pageNum);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/month/sum/{targetYm}")
    public ResponseEntity<List<LedgerEntryDailySumResponse>> getLedgerEntriesDailySum(@AuthenticationPrincipal Long userId, @PathVariable String targetYm){
        List<LedgerEntryDailySumResponse> response =  ledgerService.getLedgerEntriesDailySum(userId, targetYm);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Long> saveLedgerEntry(@AuthenticationPrincipal Long userId, @RequestBody CreateLedgerEntryRequest reqDto){
        Long response = ledgerService.saveLedgerEntry(userId, reqDto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateLedgerEntry(@AuthenticationPrincipal Long userId, @PathVariable Long id, @RequestBody UpdateLedgerEntryRequest reqDto){
        Long response = ledgerService.updateLedgerEntry(userId, id, reqDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLedgerEntry(@AuthenticationPrincipal Long userId, @PathVariable Long id) {
        ledgerService.deleteLedgerEntry(userId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/date/{targetDate}")
    public ResponseEntity<List<LedgerEntryDetailResponse>> getLedgerEntriesByDate(@AuthenticationPrincipal Long userId, @PathVariable String targetDate){
        List<LedgerEntryDetailResponse> responses = ledgerService.getLedgerEntriesByDate(userId, targetDate);
        return ResponseEntity.ok(responses);
    }

}
