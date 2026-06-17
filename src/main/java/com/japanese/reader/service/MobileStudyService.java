package com.japanese.reader.service;

import com.japanese.reader.dto.MobileStudyRecord;
import com.japanese.reader.dto.StudyRecordDTO;

import java.util.List;
import java.util.Map;

/**
 * 移动端学习业务接口
 */
public interface MobileStudyService {

    /**
     * 提交学习记录
     */
    MobileStudyRecord addRecord(StudyRecordDTO dto);

    /**
     * 获取用户学习历史
     */
    List<MobileStudyRecord> getRecordsByUser(Long userId);

    /**
     * 获取用户学习统计
     */
    Map<String, Long> getStudyStats(Long userId);
}
