package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.ledger.dto.LedgerEntryDto;
import com.future.micklemuckle.modules.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/")
    public List<LedgerEntryDto> getLedgerEntryList(@Param("entryType") String entryType) {
        return ledgerService.getLedgerEntryList(entryType);
    }

}
