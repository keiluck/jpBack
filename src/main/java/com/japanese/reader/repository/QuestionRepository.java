package com.japanese.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japanese.reader.dto.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);
}
