package com.future.micklemuckle.modules.asset.entity;

import com.future.micklemuckle.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "asset")
public class Asset extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "asset_category_id")
    private Long assetCategoryId;

    @NotNull
    private String name;

    @Column(name = "institution_name")
    private String institutionName;

    @NotNull
    private String isDebt;

    private String memo;

    @NotNull
    private Integer sortOrder;

    @NotNull
    private String isActive;
}
