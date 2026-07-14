package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.dto.LoginRequest;
import com.japanese.reader.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "后台登录", description = "admin 账号登录")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "admin 登录，成功返回 JWT token")
    public ApiResponse<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ApiResponse.success(Map.of("token", token, "username", request.getUsername()));
    }
}
