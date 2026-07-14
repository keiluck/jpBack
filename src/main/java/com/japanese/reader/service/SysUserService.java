package com.japanese.reader.service;

import com.japanese.reader.dto.PageResult;
import com.japanese.reader.dto.SysUser;
import com.japanese.reader.dto.SysUserRequest;

public interface SysUserService {
    PageResult<SysUser> page(String keyword, String status, int page, int size);

    SysUser create(SysUserRequest request);

    /** 编辑基本信息（realName / status），用户名不可改，密码不在此接口处理 */
    SysUser update(Long id, SysUserRequest request);

    /** 重置密码（独立接口） */
    void resetPassword(Long id, String newPassword);

    /** 软删除：is_deleted=1 */
    void softDelete(Long id);
}
