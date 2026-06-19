package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.asset.dto.AssetListResponse;
import com.future.micklemuckle.modules.asset.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/assets")
@RestController
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping
    public ResponseEntity<List<AssetListResponse>> getAll(@AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(assetService.findAllByUserId(userId));
    }
}
