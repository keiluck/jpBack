package com.japanese.reader.mapper;

import org.springframework.stereotype.Component;

import com.japanese.reader.dto.Article;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Article Entity 转换工具（用于列表摘要，隐藏不必要字段）
 */
@Component
public class ArticleMapper {

    /**
     * 将 Article 列表转为摘要（不含 sentences 详情，减少数据量）
     */
    public List<ArticleSummary> toSummaryList(List<Article> articles) {
        return articles.stream().map(this::toSummary).collect(Collectors.toList());
    }

    public ArticleSummary toSummary(Article article) {
        return new ArticleSummary(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getAudioUrl(),
                article.getSentences() != null ? article.getSentences().size() : 0,
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }

    /**
     * 文章摘要（列表页使用）
     */
    public static class ArticleSummary {
        private String id;
        private String title;
        private String content;
        private String audioUrl;
        private int sentenceCount;
        private String createdAt;
        private String updatedAt;

        public ArticleSummary(String id, String title, String content, String audioUrl,
                              int sentenceCount, String createdAt, String updatedAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.audioUrl = audioUrl;
            this.sentenceCount = sentenceCount;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public String getAudioUrl() { return audioUrl; }
        public int getSentenceCount() { return sentenceCount; }
        public String getCreatedAt() { return createdAt; }
        public String getUpdatedAt() { return updatedAt; }
    }
}
