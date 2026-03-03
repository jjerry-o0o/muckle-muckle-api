package com.future.micklemuckle.modules.ledger.repository;

import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * LedgerRepository
 *
 * @author : future
 * @date : 2026-02-27
 */
public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    @Query("SELECT a FROM LedgerEntry a WHERE a.entryType = :entryType")
    List<LedgerEntry> findLedgerEntryByEntryType(@Param("entryType") String entryType);

    @Query("SELECT a FROM LedgerEntry a WHERE a.entryDate >= :startDate AND a.entryDate < :endDate")
    List<LedgerEntry> findLedgerEntriesByEntryDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
