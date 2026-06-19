package com.future.micklemuckle.modules.asset.repository;

import com.future.micklemuckle.modules.asset.entity.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Long> {
    List<AssetCategory> findAllByUserIdAndIsActiveOrderBySortOrder(Long userId, String isActive);
}
