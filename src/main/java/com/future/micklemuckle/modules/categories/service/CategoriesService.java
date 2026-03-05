package com.future.micklemuckle.modules.categories.service;

import com.future.micklemuckle.modules.categories.dto.CategoryDto;
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

    public CategoryDto getCategoryByCategoryId(String id) {
        return CategoryDto.fromEntity(categoriesRepository.findCategoryByCategoryId(id));
    }
}
