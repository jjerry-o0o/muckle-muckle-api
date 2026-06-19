package com.future.micklemuckle.modules.asset.dto;

import java.util.List;

public record AssetListResponse(
        Long categoryId,
        String categoryName,
        List<AssetItemResponse> items
) {}
