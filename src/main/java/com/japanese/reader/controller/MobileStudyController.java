package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.dto.MobileStudyRecord;
import com.japanese.reader.dto.StudyRecordDTO;
import com.japanese.reader.service.MobileStudyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 移动端学习记录接口
 */
@RestController
@RequestMapping("/api/mobile/study")
@RequiredArgsConstructor
@Tag(name = "Mobile学习接口", description = "移动端学习记录提交与查询")
public class MobileStudyController {

    private final MobileStudyService mobileStudyService;

    @PostMapping("/record")
    @Operation(summary = "提交学习记录", description = "用户完成学习后提交记录")
    public ApiResponse<MobileStudyRecord> addRecord(@Valid @RequestBody StudyRecordDTO dto) {
        return ApiResponse.success(mobileStudyService.addRecord(dto));
    }

    @GetMapping("/records/{userId}")
    @Operation(summary = "获取学习历史", description = "按时间倒序返回用户全部学习记录")
    public ApiResponse<List<MobileStudyRecord>> getRecords(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId) {
        return ApiResponse.success(mobileStudyService.getRecordsByUser(userId));
    }

    @GetMapping("/stats/{userId}")
    @Operation(summary = "获取学习统计", description = "返回文章学习总数等统计数据")
    public ApiResponse<Map<String, Long>> getStats(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId) {
        return ApiResponse.success(mobileStudyService.getStudyStats(userId));
    }
}
