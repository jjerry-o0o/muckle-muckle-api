package com.future.micklemuckle.modules.categories.repository;

import com.future.micklemuckle.modules.categories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoriesRepository
 *
 * @author : future
 * @date : 2026-03-05
 */
public interface CategoriesRepository extends JpaRepository<Category, Long> {
}
