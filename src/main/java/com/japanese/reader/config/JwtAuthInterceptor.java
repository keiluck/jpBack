package com.japanese.reader.config;

import com.japanese.reader.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;

/**
 * 保护 /api/admin/**（/api/admin/login 除外，见 WebMvcConfig 的 excludePathPatterns）。
 * 未携带有效 token 返回真实 HTTP 401（区别于业务错误的包裹式 200），前端据此跳转登录页。
 */
@Component
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // CORS 预检请求直接放行
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String username = authService.validateToken(header.substring(7));
            if (username != null) {
                request.setAttribute("adminUsername", username);
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream().write(
                "{\"code\":401,\"message\":\"未登录或登录已过期\",\"data\":null}".getBytes(StandardCharsets.UTF_8));
        return false;
    }
}
