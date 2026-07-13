package com.japanese.reader.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.japanese.reader.dto.Question;
import com.japanese.reader.exception.BusinessException;
import com.japanese.reader.repository.QuestionRepository;
import com.japanese.reader.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final QuestionRepository questionRepository;
    private final ObjectMapper objectMapper;

    @Value("${file.upload.path:uploads/audio}")
    private String audioUploadPath;

    private static final List<String> ALLOWED_AUDIO_EXTENSIONS =
            List.of(".mp3", ".m4a", ".wav", ".aac", ".ogg");

    @Override
    public String uploadAudio(MultipartFile file) {
        if (file.isEmpty()) throw new BusinessException("文件不能为空");
        String originalName = file.getOriginalFilename();
        if (originalName == null || ALLOWED_AUDIO_EXTENSIONS.stream()
                .noneMatch(ext -> originalName.toLowerCase().endsWith(ext))) {
            throw new BusinessException("只支持 mp3 / m4a / wav / aac / ogg 格式音频");
        }
        try {
            Path dir = Paths.get(audioUploadPath);
            Files.createDirectories(dir);
            String fileName = UUID.randomUUID().toString().substring(0, 8) + "_" + originalName;
            Path filePath = dir.resolve(fileName);
            // transferTo 对相对路径会基于临时目录解析，必须转绝对路径
            file.transferTo(filePath.toAbsolutePath().toFile());
            return "/audio/" + fileName;
        } catch (IOException e) {
            throw new BusinessException("音频上传失败：" + e.getMessage());
        }
    }

    @Override
    public int importQuestionsFromFile(MultipartFile file) {
        if (file.isEmpty()) throw new BusinessException("文件不能为空");
        try {
            List<Question> questions = objectMapper.readValue(
                file.getInputStream(),
                new TypeReference<List<Question>>() {}
            );
            questions.forEach(q -> {
                if (q.getOptions() != null) {
                    q.getOptions().forEach(opt -> opt.setQuestion(q));
                }
            });
            questionRepository.saveAll(questions);
            return questions.size();
        } catch (IOException e) {
            throw new BusinessException("题库文件解析失败，请确认 JSON 格式正确");
        }
    }
}
