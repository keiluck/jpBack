package com.japanese.reader.service;

import com.japanese.reader.model.Question;
import java.util.List;

public interface QuizService {
    List<String> getCategories();
    List<Question> getByCategory(String category, String lang);
    Question getQuestionById(Long id);
    boolean checkAnswer(Long id, String answer);
}
