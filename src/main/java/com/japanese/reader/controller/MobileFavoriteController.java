package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.dto.MobileFavorite;
import com.japanese.reader.exception.BusinessException;
import com.japanese.reader.repository.MobileFavoriteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 移动端收藏接口
 */
@RestController
@RequestMapping("/api/mobile/favorite")
@RequiredArgsConstructor
@Tag(name = "Mobile收藏接口", description = "移动端收藏文章功能")
public class MobileFavoriteController {

    private final MobileFavoriteRepository favoriteRepository;

    @PostMapping
    @Operation(summary = "添加收藏", description = "收藏指定文章")
    public ApiResponse<MobileFavorite> addFavorite(
            @Parameter(description = "用户ID") @RequestParam Long userId,
            @Parameter(description = "目标ID（文章ID）") @RequestParam Long targetId,
            @Parameter(description = "收藏类型：article") @RequestParam(defaultValue = "article") String targetType) {

        if (favoriteRepository.existsByUserIdAndTargetIdAndTargetType(userId, targetId, targetType)) {
            throw new BusinessException("已经收藏过了");
        }
        MobileFavorite favorite = MobileFavorite.builder()
                .userId(userId)
                .targetId(targetId)
                .targetType(targetType)
                .build();
        return ApiResponse.success(favoriteRepository.save(favorite));
    }

    @DeleteMapping
    @Transactional
    @Operation(summary = "取消收藏", description = "取消对指定文章的收藏")
    public ApiResponse<Void> removeFavorite(
            @Parameter(description = "用户ID") @RequestParam Long userId,
            @Parameter(description = "目标ID（文章ID）") @RequestParam Long targetId,
            @Parameter(description = "收藏类型：article") @RequestParam(defaultValue = "article") String targetType) {

        if (!favoriteRepository.existsByUserIdAndTargetIdAndTargetType(userId, targetId, targetType)) {
            throw new BusinessException("该收藏不存在");
        }
        favoriteRepository.deleteByUserIdAndTargetIdAndTargetType(userId, targetId, targetType);
        return ApiResponse.success();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "获取收藏列表", description = "获取用户全部收藏（可按类型筛选）")
    public ApiResponse<List<MobileFavorite>> getFavorites(
            @Parameter(description = "用户ID") @PathVariable Long userId,
            @Parameter(description = "类型筛选，不传则返回全部") @RequestParam(required = false) String targetType) {

        List<MobileFavorite> list = (targetType != null && !targetType.isBlank())
                ? favoriteRepository.findByUserIdAndTargetType(userId, targetType)
                : favoriteRepository.findByUserId(userId);
        return ApiResponse.success(list);
    }

    @GetMapping("/check")
    @Operation(summary = "检查是否已收藏", description = "判断用户是否收藏了某篇文章")
    public ApiResponse<Boolean> checkFavorite(
            @Parameter(description = "用户ID") @RequestParam Long userId,
            @Parameter(description = "目标ID") @RequestParam Long targetId,
            @Parameter(description = "类型：article") @RequestParam(defaultValue = "article") String targetType) {

        return ApiResponse.success(
                favoriteRepository.existsByUserIdAndTargetIdAndTargetType(userId, targetId, targetType));
    }
}
