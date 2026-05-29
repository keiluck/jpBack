package com.japanese.reader.dto;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;       // AWS / IT / JAPANESE
    private String difficulty;     // EASY / MEDIUM / HARD
    private String type;           // SINGLE / MULTIPLE

    private String titleZh;
    private String titleJa;
    private String titleEn;

    private String explanationZh;
    private String explanationJa;
    private String explanationEn;

    private String createdAt;
    private String updatedAt;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<QuestionOption> options;
}
