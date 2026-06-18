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
import java.util.Optional;

/**
 * LedgerRepository
 *
 * @author : future
 * @date : 2026-02-27
 */
public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    Optional<LedgerEntry> findByUserIdAndId(Long userId, Long id);

    List<LedgerEntry> findByUserIdAndEntryDateBetween(Long userId, LocalDate start, LocalDate end);

    @Query("SELECT l FROM LedgerEntry l WHERE l.userId = :userId")
    Slice<LedgerEntry> findByUserIdWithSlice(Long userId, Pageable pageable);

    @Query("SELECT l.entryDate as entryDate, l.entryType as entryType, SUM(l.amount) as amount" +
            " FROM LedgerEntry l" +
            " WHERE l.userId = :userId" +
            " AND l.entryDate >= :start" +
            " AND l.entryDate <= :end" +
            " GROUP BY l.entryDate, l.entryType" +
            " ORDER BY l.entryDate ASC, l.entryType DESC")
    List<LedgerSumProjection> findByUserIdAmountSumByDateAndType(Long userId, LocalDate start, LocalDate end);

    List<LedgerEntry> findByUserIdAndEntryDate(Long userId, LocalDate targetDate);
}
