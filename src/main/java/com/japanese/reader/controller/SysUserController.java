package com.japanese.reader.controller;

import com.japanese.reader.common.ApiResponse;
import com.japanese.reader.dto.PageResult;
import com.japanese.reader.dto.SysUser;
import com.japanese.reader.dto.SysUserRequest;
import com.japanese.reader.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** 路径在 /api/admin/** 下，由 JwtAuthInterceptor 统一鉴权 */
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "业务用户的增删改查（软删除）")
public class SysUserController {

    private final SysUserService sysUserService;

    @GetMapping
    @Operation(summary = "分页查询用户，支持用户名/姓名模糊搜索和状态筛选")
    public ApiResponse<PageResult<SysUser>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") String status) {
        return ApiResponse.success(sysUserService.page(keyword, status, page, size));
    }

    @PostMapping
    @Operation(summary = "新增用户（密码 BCrypt 加密存储）")
    public ApiResponse<SysUser> create(@RequestBody SysUserRequest request) {
        return ApiResponse.success(sysUserService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑用户基本信息（不含密码）")
    public ApiResponse<SysUser> update(@PathVariable Long id, @RequestBody SysUserRequest request) {
        return ApiResponse.success(sysUserService.update(id, request));
    }

    @PutMapping("/{id}/password")
    @Operation(summary = "重置密码")
    public ApiResponse<Void> resetPassword(@PathVariable Long id, @RequestBody SysUserRequest request) {
        sysUserService.resetPassword(id, request.getPassword());
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户（软删除）")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        sysUserService.softDelete(id);
        return ApiResponse.success(null);
    }
}
