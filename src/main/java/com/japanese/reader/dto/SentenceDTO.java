package com.japanese.reader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 句子DTO - 用于接收前端数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SentenceDTO {
    private Long id;
    private String sentenceId; // 如: s01, s02
    private String text;
    private String translation;
    private Double startTime;
    private Double endTime;
    private List<RubyWordDTO> rubyWords;
}
