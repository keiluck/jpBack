package com.japanese.reader.service;

import java.util.List;

import com.japanese.reader.dto.Article;

/**
 * 文章业务接口
 */
public interface ArticleService {

    /**
     * 获取所有文章
     */
    List<Article> getAllArticles();

    /**
     * 根据 ID 获取文章详情
     */
    Article getArticleById(String id);

    /**
     * 获取 mock 文章（开发调试用）
     */
    Article getMockArticle();
}
