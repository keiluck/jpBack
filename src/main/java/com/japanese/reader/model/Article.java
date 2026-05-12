package com.japanese.reader.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    @Id
    private String id;
    private String title;
    private String content;
    private String audioUrl;
    private String createdAt;
    private String updatedAt;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sentence> sentences;
}
