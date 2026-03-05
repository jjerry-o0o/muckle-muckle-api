package com.future.micklemuckle.modules.categories.repository;

import com.future.micklemuckle.modules.categories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * CategoriesRepository
 *
 * @author : future
 * @date : 2026-03-05
 */
public interface CategoriesRepository extends JpaRepository<Category, Long> {

    @Query("SELECT a FROM Category a WHERE a.categoryId = :id")
    Category findCategoryByCategoryId(@Param("id") String id);
}
