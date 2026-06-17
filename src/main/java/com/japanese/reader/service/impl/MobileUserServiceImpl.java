package com.japanese.reader.service.impl;

import com.japanese.reader.dto.MobileLoginDTO;
import com.japanese.reader.dto.MobileRegisterDTO;
import com.japanese.reader.dto.MobileUserVO;
import com.japanese.reader.service.MobileUserService;
import org.springframework.stereotype.Service;

@Service
public class MobileUserServiceImpl implements MobileUserService {

    @Override
    public MobileUserVO register(MobileRegisterDTO dto) {
        return new MobileUserVO();
    }

    @Override
    public MobileUserVO login(MobileLoginDTO dto) {
        return new MobileUserVO();
    }

    @Override
    public MobileUserVO getUserInfo(Long userId) {
        return new MobileUserVO();
    }

    @Override
    public MobileUserVO updateUserInfo(Long userId, MobileRegisterDTO dto) {
        return new MobileUserVO();
    }
}