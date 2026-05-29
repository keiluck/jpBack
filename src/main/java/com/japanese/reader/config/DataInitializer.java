package com.japanese.reader.config;

import com.japanese.reader.dto.Article;
import com.japanese.reader.dto.Sentence;
import com.japanese.reader.repository.ArticleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
public class DataInitializer {
    private final ArticleRepository articleRepository;

    public DataInitializer(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostConstruct
    public void init() {
        if (articleRepository.count() == 0) {
            Article article = Article.builder()
                    .id("1")
                    .title("日本の四季")
                    .content("日本は四季が美しい国です。春には桜が咲き、夏は緑に包まれ、秋は紅葉が美しく、冬は白い雪が降ります。")
                    .audioUrl("https://example.com/audio/japanese-lesson-1.mp3")
                    .createdAt("2024-01-01T00:00:00Z")
                    .updatedAt("2024-01-01T00:00:00Z")
                    .build();

            Sentence s1 = Sentence.builder().id("1-1").text("日本は四季が美しい国です。")
                    .translation("日本是一个四季分明的国家。")
                    .startTime(0).endTime(3.5).article(article).build();
            Sentence s2 = Sentence.builder().id("1-2").text("春には桜が咲きます。")
                    .translation("春天樱花盛开。")
                    .startTime(3.5).endTime(6.8).article(article).build();
            Sentence s3 = Sentence.builder().id("1-3").text("夏は緑に包まれます。")
                    .translation("夏天被绿色环绕。")
                    .startTime(6.8).endTime(9.5).article(article).build();
            Sentence s4 = Sentence.builder().id("1-4").text("秋は紅葉が美しいです。")
                    .translation("秋天的红叶很美。")
                    .startTime(9.5).endTime(12.8).article(article).build();
            Sentence s5 = Sentence.builder().id("1-5").text("冬は白い雪が降ります。")
                    .translation("冬天下白雪。")
                    .startTime(12.8).endTime(16).article(article).build();

            article.setSentences(Arrays.asList(s1, s2, s3, s4, s5));
            articleRepository.save(article);
        }
    }
}
