package com.future.micklemuckle.modules.categories.dto;

import com.future.micklemuckle.modules.categories.entity.Category;

/**
 * CategoriesDto
 *
 * @author : future
 * @date : 2026-03-05
 */
public record CategoriesResponse (
        Long categoryId,
        String name,
        String color,
        int sortOrder
) {
    public static CategoriesResponse fromEntity(Category entity) {
        return new CategoriesResponse(
                entity.getId(),
                entity.getName(),
                entity.getColor(),
                entity.getSortOrder()
        );
    }
}
