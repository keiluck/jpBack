package com.japanese.reader.service;

import java.util.List;

import com.japanese.reader.dto.Question;

public interface QuizService {
    List<String> getCategories();
    List<Question> getByCategory(String category, String lang);
    Question getQuestionById(Long id);
    boolean checkAnswer(Long id, String answer);
}
