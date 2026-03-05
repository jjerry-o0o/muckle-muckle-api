package com.future.micklemuckle.modules.categories.entity;

import com.future.micklemuckle.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * LedgerCategory Entity Class
 *
 * @author : future
 * @date : 2026-02-27
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotNull
    private String name;
    @NotNull
    private String color;
    @NotNull
    private Integer sortOrder;

}
