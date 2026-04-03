package com.future.micklemuckle.modules.ledger.repository;

import com.future.micklemuckle.modules.ledger.dto.LedgerEntryDailySumResponse;
import com.future.micklemuckle.modules.ledger.dto.LedgerSumProjection;
import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * LedgerRepository
 *
 * @author : future
 * @date : 2026-02-27
 */
public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    List<LedgerEntry> findByEntryDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT l FROM LedgerEntry l")
    Slice<LedgerEntry> findWithSlice(Pageable pageable);

    @Query("SELECT l.entryDate as entryDate, l.entryType as entryType, SUM(l.amount) as amount" +
            " FROM LedgerEntry l" +
            " WHERE l.entryDate >= :start" +
            " AND l.entryDate <= :end" +
            " GROUP BY l.entryDate, l.entryType" +
            " ORDER BY l.entryDate ASC, l.entryType DESC")
    List<LedgerSumProjection> findAmountSumByDateAndType(LocalDate start, LocalDate end);

    List<LedgerEntry> findByEntryDate(LocalDate targetDate);
}
