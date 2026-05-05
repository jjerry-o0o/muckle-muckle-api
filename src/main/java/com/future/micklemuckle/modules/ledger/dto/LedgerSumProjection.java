package com.future.micklemuckle.modules.ledger.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * LedgerSumProjection
 *
 * @author : future
 * @date : 2026-03-17
 */
public interface LedgerSumProjection {
    LocalDate getEntryDate();
    String getEntryType();
    BigDecimal getAmount();
}
