package com.japanese.reader.repository;

import com.japanese.reader.dto.MobileStudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 移动端学习记录 Repository
 */
@Repository
public interface MobileStudyRecordRepository extends JpaRepository<MobileStudyRecord, Long> {

    List<MobileStudyRecord> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("SELECT COUNT(r) FROM MobileStudyRecord r WHERE r.userId = :userId AND r.studyType = :studyType")
    Long countByUserIdAndStudyType(Long userId, String studyType);
}
