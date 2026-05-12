package com.japanese.reader.service.impl;

import com.japanese.reader.exception.BusinessException;
import com.japanese.reader.model.Article;
import com.japanese.reader.model.Question;
import com.japanese.reader.repository.ArticleRepository;
import com.japanese.reader.repository.QuestionRepository;
import com.japanese.reader.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final QuestionRepository questionRepository;
    private final ArticleRepository articleRepository;

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
    }

    // ========== 题库 ==========

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question createQuestion(Question question) {
        question.setCreatedAt(now());
        question.setUpdatedAt(now());
        if (question.getOptions() != null) {
            question.getOptions().forEach(opt -> opt.setQuestion(question));
        }
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Question existing = questionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(404, "题目不存在，id=" + id));
        question.setId(existing.getId());
        question.setCreatedAt(existing.getCreatedAt());
        question.setUpdatedAt(now());
        if (question.getOptions() != null) {
            question.getOptions().forEach(opt -> opt.setQuestion(question));
        }
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new BusinessException(404, "题目不存在，id=" + id);
        }
        questionRepository.deleteById(id);
    }

    @Override
    public int importQuestions(List<Question> questions) {
        questions.forEach(q -> {
            q.setCreatedAt(now());
            q.setUpdatedAt(now());
            if (q.getOptions() != null) {
                q.getOptions().forEach(opt -> opt.setQuestion(q));
            }
        });
        questionRepository.saveAll(questions);
        return questions.size();
    }

    // ========== 文章 ==========

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article createArticle(Article article) {
        article.setCreatedAt(now());
        article.setUpdatedAt(now());
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(String id, Article article) {
        Article existing = articleRepository.findById(id)
                .orElseThrow(() -> new BusinessException(404, "文章不存在，id=" + id));
        article.setId(existing.getId());
        article.setCreatedAt(existing.getCreatedAt());
        article.setUpdatedAt(now());
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(String id) {
        if (!articleRepository.existsById(id)) {
            throw new BusinessException(404, "文章不存在，id=" + id);
        }
        articleRepository.deleteById(id);
    }
}
