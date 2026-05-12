package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.model.Question;
import com.japanese.reader.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
@Tag(name = "刷题接口", description = "题目分类、列表、答题")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/categories")
    @Operation(summary = "获取所有分类", description = "返回 AWS / IT / JAPANESE 等分类")
    public ApiResponse<List<String>> getCategories() {
        return ApiResponse.success(quizService.getCategories());
    }

    @GetMapping("/{category}")
    @Operation(summary = "根据分类获取题目", description = "支持语言参数 zh/ja/en")
    public ApiResponse<List<Question>> getByCategory(
        @Parameter(description = "分类名称", example = "AWS")
        @PathVariable String category,
        @Parameter(description = "语言", example = "zh")
        @RequestParam(defaultValue = "zh") String lang
    ) {
        return ApiResponse.success(quizService.getByCategory(category, lang));
    }

    @GetMapping("/question/{id}")
    @Operation(summary = "获取题目详情")
    public ApiResponse<Question> getQuestion(
        @Parameter(description = "题目ID", example = "1")
        @PathVariable Long id
    ) {
        return ApiResponse.success(quizService.getQuestionById(id));
    }

    @PostMapping("/question/{id}/answer")
    @Operation(summary = "提交答案", description = "返回是否正确")
    public ApiResponse<Boolean> submitAnswer(
        @PathVariable Long id,
        @Parameter(description = "答案选项", example = "A")
        @RequestParam String answer
    ) {
        return ApiResponse.success(quizService.checkAnswer(id, answer));
    }
}
