package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/audio")
    public ApiResponse<String> uploadAudio(@RequestParam("file") MultipartFile file) {
        return ApiResponse.success(fileUploadService.uploadAudio(file));
    }

    @PostMapping("/questions")
    public ApiResponse<Integer> uploadQuestions(@RequestParam("file") MultipartFile file) {
        return ApiResponse.success(fileUploadService.importQuestionsFromFile(file));
    }
}
