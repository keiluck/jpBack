package com.japanese.reader.service;

import java.util.List;

import com.japanese.reader.dto.Article;
import com.japanese.reader.dto.Question;

public interface AdminService {
    // 题库1
    List<Question> getAllQuestions();
    Question createQuestion(Question question);
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
    int importQuestions(List<Question> questions);

    // 文章
    List<Article> getAllArticles();
    Article createArticle(Article article);
    Article updateArticle(String id, Article article);
    void deleteArticle(String id);
}
