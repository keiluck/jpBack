package com.japanese.reader.service.impl;

import com.japanese.reader.exception.BusinessException;
import com.japanese.reader.model.Question;
import com.japanese.reader.repository.QuestionRepository;
import com.japanese.reader.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuestionRepository questionRepository;

    @Override
    public List<String> getCategories() {
        return Arrays.asList("AWS", "IT", "JAPANESE");
    }

    @Override
    public List<Question> getByCategory(String category, String lang) {
        return questionRepository.findByCategory(category.toUpperCase());
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(404, "题目不存在，id=" + id));
    }

    @Override
    public boolean checkAnswer(Long id, String answer) {
        Question question = getQuestionById(id);
        return question.getOptions().stream()
                .anyMatch(opt -> opt.getOptionKey().equalsIgnoreCase(answer)
                        && Boolean.TRUE.equals(opt.getIsCorrect()));
    }
}
