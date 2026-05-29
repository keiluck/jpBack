package com.japanese.reader.service.impl;

import com.japanese.reader.dto.Article;
import com.japanese.reader.dto.RubyWord;
import com.japanese.reader.dto.Sentence;
import com.japanese.reader.exception.BusinessException;
import com.japanese.reader.repository.ArticleRepository;
import com.japanese.reader.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文章业务实现
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(String id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new BusinessException(404, "文章不存在，id=" + id));
    }

    @Override
    public Article getMockArticle() {
        List<Sentence> sentences = Arrays.asList(
            Sentence.builder()
                .id("1-1")
                .text("はじめまして、ケイと申します。")
                .translation("初次见面，我叫Kei。")
                .startTime(0).endTime(3.5)
                .rubyWords(Arrays.asList(
                    new RubyWord("はじめまして", null),
                    new RubyWord("、", null),
                    new RubyWord("ケイ", null),
                    new RubyWord("と", null),
                    new RubyWord("申", "もう"),
                    new RubyWord("します", null),
                    new RubyWord("。", null)
                )).build(),
            Sentence.builder()
                .id("1-2")
                .text("フロントエンドエンジニアとして働いています。")
                .translation("我是一名前端工程师。")
                .startTime(3.5).endTime(6.8)
                .rubyWords(Arrays.asList(
                    new RubyWord("フロントエンドエンジニア", null),
                    new RubyWord("として", null),
                    new RubyWord("働", "はたら"),
                    new RubyWord("いて", null),
                    new RubyWord("います", null),
                    new RubyWord("。", null)
                )).build(),
            Sentence.builder()
                .id("1-3")
                .text("現在はJavaとAWSも勉強しています。")
                .translation("我现在也在学习Java和AWS。")
                .startTime(6.8).endTime(9.5)
                .rubyWords(Arrays.asList(
                    new RubyWord("現在", "げんざい"),
                    new RubyWord("は", null),
                    new RubyWord("Java", null),
                    new RubyWord("と", null),
                    new RubyWord("AWS", null),
                    new RubyWord("も", null),
                    new RubyWord("勉強", "べんきょう"),
                    new RubyWord("しています", null),
                    new RubyWord("。", null)
                )).build(),
            Sentence.builder()
                .id("1-4")
                .text("どうぞよろしくお願いいたします。")
                .translation("请多多关照。")
                .startTime(9.5).endTime(12.8)
                .rubyWords(Arrays.asList(
                    new RubyWord("どうぞ", null),
                    new RubyWord("よろしく", null),
                    new RubyWord("お願", "ねが"),
                    new RubyWord("いいた", null),
                    new RubyWord("します", null),
                    new RubyWord("。", null)
                )).build()
        );

        return Article.builder()
                .id("1")
                .title("自我介绍")
                .content("はじめまして、ケイと申します。フロントエンドエンジニアとして働いています。現在はJavaとAWSも勉強しています。どうぞよろしくお願いいたします。")
                .audioUrl("/audio/001.mp3")
                .sentences(sentences)
                .createdAt("2024-01-01T00:00:00Z")
                .updatedAt("2024-01-01T00:00:00Z")
                .build();
    }
}
