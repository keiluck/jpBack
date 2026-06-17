package com.japanese.reader.service.impl;

import com.japanese.reader.dto.MobileStudyRecord;
import com.japanese.reader.dto.StudyRecordDTO;
import com.japanese.reader.repository.MobileStudyRecordRepository;
import com.japanese.reader.service.MobileStudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 移动端学习业务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MobileStudyServiceImpl implements MobileStudyService {

    private final MobileStudyRecordRepository studyRecordRepository;

    @Override
    public MobileStudyRecord addRecord(StudyRecordDTO dto) {
        MobileStudyRecord record = MobileStudyRecord.builder()
                .userId(dto.getUserId())
                .articleId(dto.getArticleId())
                .studyType(dto.getStudyType())
                .duration(dto.getDuration() != null ? dto.getDuration() : 0)
                .score(dto.getScore() != null ? dto.getScore() : 0)
                .build();

        MobileStudyRecord saved = studyRecordRepository.save(record);
        log.info("学习记录保存成功: userId={}, type={}", dto.getUserId(), dto.getStudyType());
        return saved;
    }

    @Override
    public List<MobileStudyRecord> getRecordsByUser(Long userId) {
        return studyRecordRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public Map<String, Long> getStudyStats(Long userId) {
        Map<String, Long> stats = new HashMap<>();
        stats.put("articleCount", studyRecordRepository.countByUserIdAndStudyType(userId, "article"));
        stats.put("totalCount", studyRecordRepository.countByUserIdAndStudyType(userId, "article"));
        return stats;
    }
}
