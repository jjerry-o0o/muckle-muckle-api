package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.categories.dto.CategoriesResponse;
import com.future.micklemuckle.modules.categories.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/api/categories")
@RestController
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriesResponse> getOne(@PathVariable Long id) {
        CategoriesResponse response = categoriesService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoriesResponse>> getAll(@AuthenticationPrincipal Long userId) {
        List<CategoriesResponse> response = categoriesService.findAllByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
