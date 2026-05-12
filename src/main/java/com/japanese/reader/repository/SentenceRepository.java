package com.japanese.reader.repository;

import com.japanese.reader.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepository extends JpaRepository<Sentence, String> {
}
