package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.dto.Article;
import com.japanese.reader.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章接口层
 */
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@Tag(name = "文章接口", description = "文章列表、详情、跟读数据")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 获取所有文章（摘要列表）
     */
    @GetMapping
    @Operation(summary = "获取所有文章", description = "返回文章摘要列表")
    public ApiResponse<List<Article>> getAllArticles() {
        return ApiResponse.success(articleService.getAllArticles());
    }

    /**
     * 根据 ID 获取文章详情（含 sentences、rubyWords）
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取文章详情", description = "返回文章完整数据，含句子和振假名")
    public ApiResponse<Article> getArticleById(
            @Parameter(description = "文章ID", example = "1")
            @PathVariable String id
    ) {
        return ApiResponse.success(articleService.getArticleById(id));
    }

    /**
     * 获取 mock 文章（开发调试用）
     */
    @GetMapping("/mock")
    @Operation(summary = "获取 Mock 文章", description = "开发调试用，返回自我介绍示例数据")
    public ApiResponse<Article> getMockArticle() {
        return ApiResponse.success(articleService.getMockArticle());
    }
}
