package com.future.micklemuckle.modules.categories.service;

import com.future.micklemuckle.common.exception.NotFoundException;
import com.future.micklemuckle.modules.categories.dto.CategoryDto;
import com.future.micklemuckle.modules.categories.entity.Category;
import com.future.micklemuckle.modules.categories.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoriesService
 *
 * @author : future
 * @date : 2026-03-05
 */
@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public CategoryDto getCategoryByCategoryId(Long id) {
        Category category = categoriesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("category not found"));

        return CategoryDto.fromEntity(category);
    }
}
