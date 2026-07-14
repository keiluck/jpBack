package com.japanese.reader.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.japanese.reader.dto.AdminUser;
import com.japanese.reader.exception.BusinessException;
import com.japanese.reader.repository.AdminUserRepository;
import com.japanese.reader.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminUserRepository adminUserRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expire-hours:24}")
    private long expireHours;

    @Override
    public String login(String username, String password) {
        AdminUser user = adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(401, "用户名或密码错误"));
        if (!BCrypt.checkpw(password, user.getPasswordHash())) {
            throw new BusinessException(401, "用户名或密码错误");
        }
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireHours * 3600_000L))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    @Override
    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(jwtSecret))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
