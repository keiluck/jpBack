package com.japanese.reader.service;

import com.japanese.reader.dto.MobileLoginDTO;
import com.japanese.reader.dto.MobileRegisterDTO;
import com.japanese.reader.dto.MobileUserVO;

/**
 * 移动端用户业务接口
 */
public interface MobileUserService {

    /**
     * 用户注册
     */
    MobileUserVO register(MobileRegisterDTO dto);

    /**
     * 用户登录
     */
    MobileUserVO login(MobileLoginDTO dto);

    /**
     * 获取用户信息
     */
    MobileUserVO getUserInfo(Long userId);

    /**
     * 更新用户信息
     */
    MobileUserVO updateUserInfo(Long userId, MobileRegisterDTO dto);
}
