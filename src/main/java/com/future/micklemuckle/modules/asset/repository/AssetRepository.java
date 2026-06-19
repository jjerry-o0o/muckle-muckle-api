package com.future.micklemuckle.modules.asset.repository;

import com.future.micklemuckle.modules.asset.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findAllByUserIdAndAssetCategoryIdAndIsActiveOrderBySortOrder(Long userId, Long assetCategoryId, String isActive);
}
