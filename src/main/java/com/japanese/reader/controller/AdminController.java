package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.dto.Article;
import com.japanese.reader.dto.Question;
import com.japanese.reader.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "后台管理接口", description = "题库管理、文章管理")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/questions")
    @Operation(summary = "获取所有题目")
    public ApiResponse<List<Question>> getAllQuestions() {
        return ApiResponse.success(adminService.getAllQuestions());
    }

    @PostMapping("/questions")
    @Operation(summary = "新建题目")
    public ApiResponse<Question> createQuestion(@RequestBody Question question) {
        return ApiResponse.success(adminService.createQuestion(question));
    }

    @PutMapping("/questions/{id}")
    @Operation(summary = "编辑题目")
    public ApiResponse<Question> updateQuestion(
        @PathVariable Long id,
        @RequestBody Question question
    ) {
        return ApiResponse.success(adminService.updateQuestion(id, question));
    }

    @DeleteMapping("/questions/{id}")
    @Operation(summary = "删除题目")
    public ApiResponse<Void> deleteQuestion(@PathVariable Long id) {
        adminService.deleteQuestion(id);
        return ApiResponse.success(null);
    }

    @PostMapping("/questions/import")
    @Operation(summary = "批量导入题库", description = "传入 JSON 数组")
    public ApiResponse<Integer> importQuestions(@RequestBody List<Question> questions) {
        return ApiResponse.success(adminService.importQuestions(questions));
    }

    @GetMapping("/articles")
    @Operation(summary = "获取所有文章")
    public ApiResponse<List<Article>> getAllArticles() {
        return ApiResponse.success(adminService.getAllArticles());
    }

    @PostMapping("/articles")
    @Operation(summary = "新建文章")
    public ApiResponse<Article> createArticle(@RequestBody Article article) {
        return ApiResponse.success(adminService.createArticle(article));
    }

    @PutMapping("/articles/{id}")
    @Operation(summary = "编辑文章")
    public ApiResponse<Article> updateArticle(
        @PathVariable String id,
        @RequestBody Article article
    ) {
        return ApiResponse.success(adminService.updateArticle(id, article));
    }

    @DeleteMapping("/articles/{id}")
    @Operation(summary = "删除文章")
    public ApiResponse<Void> deleteArticle(@PathVariable String id) {
        adminService.deleteArticle(id);
        return ApiResponse.success(null);
    }
}
