package com.japanese.reader.dto;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 移动端学习记录实体
 */
@Entity
@Table(name = "mobile_study_record")
public class MobileStudyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "article_id")
    private Long articleId;

    /** 学习类型：article */
    @Column(name = "study_type", nullable = false, length = 20)
    private String studyType;

    /** 学习时长（秒） */
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer duration = 0;

    /** 得分 */
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer score = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // -------- Getters & Setters --------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getArticleId() { return articleId; }
    public void setArticleId(Long articleId) { this.articleId = articleId; }

    public String getStudyType() { return studyType; }
    public void setStudyType(String studyType) { this.studyType = studyType; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // -------- Builder --------

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final MobileStudyRecord r = new MobileStudyRecord();
        public Builder userId(Long v) { r.userId = v; return this; }
        public Builder articleId(Long v) { r.articleId = v; return this; }
        public Builder studyType(String v) { r.studyType = v; return this; }
        public Builder duration(Integer v) { r.duration = v; return this; }
        public Builder score(Integer v) { r.score = v; return this; }
        public MobileStudyRecord build() { return r; }
    }
}
