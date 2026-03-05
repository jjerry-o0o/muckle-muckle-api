package com.future.micklemuckle.modules.categories.dto;

import com.future.micklemuckle.modules.categories.entity.Category;
import lombok.Builder;
import lombok.Getter;

/**
 * CategoriesDto
 *
 * @author : future
 * @date : 2026-03-05
 */
@Getter
@Builder
public class CategoryDto {

    private final Long categoryId;
    private final String name;
    private final String color;
    private final int sortOrder;

    public static CategoryDto fromEntity(Category entity) {
        return CategoryDto.builder()
                .categoryId(entity.getCategoryId())
                .name(entity.getName())
                .color(entity.getColor())
                .sortOrder(entity.getSortOrder())
                .build();
    }
}
