package com.japanese.reader.service.impl;

import com.japanese.reader.dto.PageResult;
import com.japanese.reader.dto.SysUser;
import com.japanese.reader.dto.SysUserRequest;
import com.japanese.reader.exception.BusinessException;
import com.japanese.reader.repository.SysUserRepository;
import com.japanese.reader.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    public static final String STATUS_ENABLED = "ENABLED";
    public static final String STATUS_DISABLED = "DISABLED";

    private final SysUserRepository sysUserRepository;

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
    }

    @Override
    public PageResult<SysUser> page(String keyword, String status, int page, int size) {
        Page<SysUser> result = sysUserRepository.search(
                keyword == null ? "" : keyword.trim(),
                status == null ? "" : status.trim(),
                PageRequest.of(Math.max(page - 1, 0), size, Sort.by(Sort.Direction.DESC, "id")));
        return new PageResult<>(result.getContent(), result.getTotalElements(), page, size);
    }

    @Override
    public SysUser create(SysUserRequest request) {
        String username = request.getUsername() == null ? "" : request.getUsername().trim();
        if (username.isEmpty()) {
            throw new BusinessException("用户名不能为空");
        }
        if (sysUserRepository.existsByUsernameAndIsDeleted(username, 0)) {
            throw new BusinessException("用户名已存在");
        }
        validatePassword(request.getPassword());
        SysUser user = SysUser.builder()
                .username(username)
                .passwordHash(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .realName(request.getRealName() == null ? "" : request.getRealName().trim())
                .status(STATUS_DISABLED.equals(request.getStatus()) ? STATUS_DISABLED : STATUS_ENABLED)
                .isDeleted(0)
                .createdAt(now())
                .updatedAt(now())
                .build();
        return sysUserRepository.save(user);
    }

    @Override
    public SysUser update(Long id, SysUserRequest request) {
        SysUser user = getActiveUser(id);
        if (STATUS_DISABLED.equals(request.getStatus()) && STATUS_ENABLED.equals(user.getStatus())) {
            ensureNotLastEnabled("禁用");
        }
        if (request.getRealName() != null) {
            user.setRealName(request.getRealName().trim());
        }
        if (request.getStatus() != null) {
            user.setStatus(STATUS_DISABLED.equals(request.getStatus()) ? STATUS_DISABLED : STATUS_ENABLED);
        }
        user.setUpdatedAt(now());
        return sysUserRepository.save(user);
    }

    @Override
    public void resetPassword(Long id, String newPassword) {
        SysUser user = getActiveUser(id);
        validatePassword(newPassword);
        user.setPasswordHash(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        user.setUpdatedAt(now());
        sysUserRepository.save(user);
    }

    @Override
    public void softDelete(Long id) {
        SysUser user = getActiveUser(id);
        if (STATUS_ENABLED.equals(user.getStatus())) {
            ensureNotLastEnabled("删除");
        }
        user.setIsDeleted(1);
        user.setUpdatedAt(now());
        sysUserRepository.save(user);
    }

    private SysUser getActiveUser(Long id) {
        SysUser user = sysUserRepository.findById(id)
                .orElseThrow(() -> new BusinessException(404, "用户不存在，id=" + id));
        if (user.getIsDeleted() == 1) {
            throw new BusinessException(404, "用户不存在，id=" + id);
        }
        return user;
    }

    /** 防呆：至少保留一个启用状态的用户 */
    private void ensureNotLastEnabled(String action) {
        if (sysUserRepository.countByStatusAndIsDeleted(STATUS_ENABLED, 0) <= 1) {
            throw new BusinessException("不能" + action + "最后一个启用状态的用户");
        }
    }

    /** 密码强度：至少 8 位，且同时包含字母和数字 */
    private void validatePassword(String password) {
        if (password == null || password.length() < 8
                || !password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")) {
            throw new BusinessException("密码至少 8 位，且需同时包含字母和数字");
        }
    }
}
