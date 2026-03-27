package com.future.micklemuckle.modules.categories.service;

import com.future.micklemuckle.common.exception.ErrorCode;
import com.future.micklemuckle.common.exception.NotFoundException;
import com.future.micklemuckle.modules.categories.dto.CategoriesResponse;
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

    public CategoriesResponse findById(Long id) {
        Category category = categoriesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        return CategoriesResponse.fromEntity(category);
    }

    public List<CategoriesResponse> findAll() {
        return categoriesRepository.findAll()
                .stream()
                .map(CategoriesResponse::fromEntity)
                .toList();
    }
}
