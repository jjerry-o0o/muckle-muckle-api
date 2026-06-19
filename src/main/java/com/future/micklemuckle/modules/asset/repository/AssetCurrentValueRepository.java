package com.future.micklemuckle.modules.asset.repository;

import com.future.micklemuckle.modules.asset.entity.AssetCurrentValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetCurrentValueRepository extends JpaRepository<AssetCurrentValue, Long> {
}
