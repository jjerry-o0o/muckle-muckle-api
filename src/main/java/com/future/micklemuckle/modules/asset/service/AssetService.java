package com.future.micklemuckle.modules.asset.service;

import com.future.micklemuckle.modules.asset.dto.AssetItemResponse;
import com.future.micklemuckle.modules.asset.dto.AssetListResponse;
import com.future.micklemuckle.modules.asset.entity.Asset;
import com.future.micklemuckle.modules.asset.entity.AssetCategory;
import com.future.micklemuckle.modules.asset.repository.AssetCategoryRepository;
import com.future.micklemuckle.modules.asset.repository.AssetCurrentValueRepository;
import com.future.micklemuckle.modules.asset.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetCategoryRepository assetCategoryRepository;
    private final AssetRepository assetRepository;
    private final AssetCurrentValueRepository assetCurrentValueRepository;

    public List<AssetListResponse> findAllByUserId(Long userId) {
        return assetCategoryRepository.findAllByUserIdAndIsActiveOrderBySortOrder(userId, "Y")
                .stream()
                .map(cat -> toAssetListResponse(userId, cat))
                .filter(r -> !r.items().isEmpty())
                .toList();
    }

    private AssetListResponse toAssetListResponse(Long userId, AssetCategory cat) {
        List<AssetItemResponse> items = assetRepository
                .findAllByUserIdAndAssetCategoryIdAndIsActiveOrderBySortOrder(userId, cat.getId(), "Y")
                .stream()
                .map(this::toAssetItemResponse)
                .toList();
        return new AssetListResponse(cat.getId(), cat.getName(), items);
    }

    private AssetItemResponse toAssetItemResponse(Asset asset) {
        BigDecimal value = assetCurrentValueRepository.findById(asset.getId())
                .map(v -> v.getCurrentValue())
                .orElse(BigDecimal.ZERO);
        return new AssetItemResponse(asset.getId(), asset.getName(), value);
    }
}
