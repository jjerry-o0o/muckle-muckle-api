package com.future.micklemuckle.modules.ledger.entity;

import com.future.micklemuckle.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * LedgerEntry Entity Class
 *
 * @author : future
 * @date : 2026-02-27
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerEntry extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private Long id;

    @NotNull
    private LocalDate entryDate;
    @NotNull
    private String entryType;
    @NotNull
    private Integer amount;
    @NotNull
    private String title;
    private String memo;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private LedgerCategory category;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod payment;

}
