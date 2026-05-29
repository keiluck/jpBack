package com.japanese.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japanese.reader.dto.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {
}
