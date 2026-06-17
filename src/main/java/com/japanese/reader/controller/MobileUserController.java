package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.dto.MobileLoginDTO;
import com.japanese.reader.dto.MobileRegisterDTO;
import com.japanese.reader.dto.MobileUserVO;
import com.japanese.reader.service.MobileUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 移动端用户接口
 */
@RestController
@RequestMapping("/api/mobile/user")
@RequiredArgsConstructor
@Tag(name = "Mobile用户接口", description = "移动端注册、登录、用户信息管理")
public class MobileUserController {

    private final MobileUserService mobileUserService;

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "新用户注册，返回用户信息和Token")
    public ApiResponse<MobileUserVO> register(@Valid @RequestBody MobileRegisterDTO dto) {
        return ApiResponse.success(mobileUserService.register(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "账号密码登录，返回用户信息和Token")
    public ApiResponse<MobileUserVO> login(@Valid @RequestBody MobileLoginDTO dto) {
        return ApiResponse.success(mobileUserService.login(dto));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "获取用户信息", description = "根据用户ID获取详细信息")
    public ApiResponse<MobileUserVO> getUserInfo(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId) {
        return ApiResponse.success(mobileUserService.getUserInfo(userId));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "更新用户信息", description = "更新昵称、手机号、邮箱等信息")
    public ApiResponse<MobileUserVO> updateUser(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId,
            @RequestBody MobileRegisterDTO dto) {
        return ApiResponse.success(mobileUserService.updateUserInfo(userId, dto));
    }
}
