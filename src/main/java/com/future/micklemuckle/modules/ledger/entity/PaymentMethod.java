package com.future.micklemuckle.modules.ledger.entity;

import com.future.micklemuckle.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PaymentMethod Entity Class
 *
 * @author : future
 * @date : 2026-02-27
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentMethod extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String methodType;
}
