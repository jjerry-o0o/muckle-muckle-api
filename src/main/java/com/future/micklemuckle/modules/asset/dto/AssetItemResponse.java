package com.future.micklemuckle.modules.asset.dto;

import java.math.BigDecimal;

public record AssetItemResponse(
        Long assetId,
        String name,
        BigDecimal currentValue
) {}
