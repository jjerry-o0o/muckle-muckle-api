package com.future.micklemuckle.modules.asset.entity;

import com.future.micklemuckle.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "asset_current_value")
public class AssetCurrentValue extends BaseTimeEntity {

    @Id
    @Column(name = "asset_id")
    private Long assetId;

    private BigDecimal quantity;
    private BigDecimal unitPrice;

    @NotNull
    private BigDecimal currentValue;

    @NotNull
    private String currencyCode;

    private BigDecimal exchangeRate;
}
