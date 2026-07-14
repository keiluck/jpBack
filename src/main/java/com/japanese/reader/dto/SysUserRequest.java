package com.japanese.reader.dto;

import lombok.Data;

/** 用户管理的新增/编辑/重置密码请求体（按接口取用对应字段） */
@Data
public class SysUserRequest {
    private String username;
    private String realName;
    private String password;
    private String status;
}
