package com.japanese.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japanese.reader.dto.Sentence;

public interface SentenceRepository extends JpaRepository<Sentence, String> {
}
