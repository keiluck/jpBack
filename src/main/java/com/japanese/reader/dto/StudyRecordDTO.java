package com.japanese.reader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 提交学习记录请求 DTO
 */
@Data
@Schema(description = "学习记录提交")
public class StudyRecordDTO {

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID", example = "1")
    private Long userId;

    @Schema(description = "文章ID", example = "1")
    private Long articleId;

    @NotBlank(message = "学习类型不能为空")
    @Schema(description = "学习类型：article", example = "article")
    private String studyType;

    @Schema(description = "学习时长(秒)", example = "120")
    private Integer duration;

    @Schema(description = "得分", example = "80")
    private Integer score;
}
