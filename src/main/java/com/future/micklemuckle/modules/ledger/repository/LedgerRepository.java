package com.future.micklemuckle.modules.ledger.repository;

import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * LedgerRepository
 *
 * @author : future
 * @date : 2026-02-27
 */
public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    @Query("SELECT a FROM LedgerEntry a WHERE a.id = :id")
    LedgerEntry findLedgerEntryByEntryId(@Param("id") Long id);

}
