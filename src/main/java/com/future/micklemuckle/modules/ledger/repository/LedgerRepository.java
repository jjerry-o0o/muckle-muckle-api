package com.future.micklemuckle.modules.ledger.repository;

import com.future.micklemuckle.modules.ledger.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * <클래스 설명>
 *
 * @author : future
 * @date : 2026-02-27
 */
public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    @Query("SELECT FROM LedgerEntry a WHERE a.entryTypr = :entryType")

}
