package com.japanese.reader.service;

public interface AuthService {
    /** 校验用户名密码，成功返回 JWT token，失败抛 BusinessException(401) */
    String login(String username, String password);

    /** 校验 token，有效返回用户名，无效返回 null */
    String validateToken(String token);
}
