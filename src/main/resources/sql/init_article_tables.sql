-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS japanese_reader CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE japanese_reader;

-- 文章表（ID使用VARCHAR）
CREATE TABLE IF NOT EXISTS articles (
    id VARCHAR(50) PRIMARY KEY COMMENT '文章ID',
    title VARCHAR(255) NOT NULL COMMENT '文章标题',
    content TEXT COMMENT '文章内容',
    audio_url VARCHAR(500) COMMENT '音频URL',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    INDEX idx_created_at (created_at),
    INDEX idx_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- 句子表
CREATE TABLE IF NOT EXISTS sentences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '句子ID',
    sentence_id VARCHAR(50) COMMENT '句子编号（如s01,s02）',
    text TEXT NOT NULL COMMENT '日文原文',
    translation TEXT COMMENT '中文翻译',
    start_time DOUBLE COMMENT '音频开始时间',
    end_time DOUBLE COMMENT '音频结束时间',
    article_id VARCHAR(50) NOT NULL COMMENT '所属文章ID',
    FOREIGN KEY (article_id) REFERENCES articles(id) ON DELETE CASCADE,
    INDEX idx_article_id (article_id),
    INDEX idx_sentence_id (sentence_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='句子表';

-- 注音单词表
CREATE TABLE IF NOT EXISTS ruby_words (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '单词ID',
    text VARCHAR(255) NOT NULL COMMENT '单词文本',
    ruby VARCHAR(255) COMMENT '假名注音',
    word_order INT COMMENT '在句子中的顺序',
    sentence_id BIGINT NOT NULL COMMENT '所属句子ID',
    FOREIGN KEY (sentence_id) REFERENCES sentences(id) ON DELETE CASCADE,
    INDEX idx_sentence_id (sentence_id),
    INDEX idx_word_order (word_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='注音单词表';
