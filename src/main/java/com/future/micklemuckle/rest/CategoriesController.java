package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.categories.dto.CategoryDto;
import com.future.micklemuckle.modules.categories.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CategoriesController
 *
 * @author : future
 * @date : 2026-03-05
 */
@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable String id) {
        return categoriesService.getCategoryByCategoryId(id);
    }

}
