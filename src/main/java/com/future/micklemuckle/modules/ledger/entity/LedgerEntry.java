package com.future.micklemuckle.modules.ledger.entity;

import com.future.micklemuckle.common.entity.BaseTimeEntity;
import com.future.micklemuckle.modules.categories.entity.Category;
import com.future.micklemuckle.modules.payment.entity.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
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
    private Category category;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod payment;

    @Builder
    public LedgerEntry(LocalDate entryDate, String entryType, Integer amount, String title, String memo, Category category, PaymentMethod payment ) {
        this.entryDate = entryDate;
        this.entryType = entryType;
        this.amount = amount;
        this.title = title;
        this.memo = memo;
        this.category = category;
        this.payment = payment;
    }
}
