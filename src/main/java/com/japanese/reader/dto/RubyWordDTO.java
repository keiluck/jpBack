package com.japanese.reader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注音单词DTO - 用于接收前端数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RubyWordDTO {
    private Long id;
    private String text;
    private String ruby;
}
