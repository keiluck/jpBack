package com.japanese.reader.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadAudio(MultipartFile file);
    int importQuestionsFromFile(MultipartFile file);
}
