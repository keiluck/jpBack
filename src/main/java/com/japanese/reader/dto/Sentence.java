package com.japanese.reader.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sentence {
    @Id
    private String id;
    private String text;
    private String translation;
    private double startTime;
    private double endTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ruby_word", joinColumns = @JoinColumn(name = "sentence_id"))
    private List<RubyWord> rubyWords;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonIgnore
    private Article article;
}
