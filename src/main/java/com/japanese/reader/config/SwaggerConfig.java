package com.japanese.reader.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Japanese Reader API")
                .description("日语学习平台 - 文章跟读 / 分类刷题 / 后台管理")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Admin")
                    .email("admin@example.com")))
            .servers(List.of(
                new Server().url("http://localhost:8080").description("本地开发环境")
            ));
    }
}
