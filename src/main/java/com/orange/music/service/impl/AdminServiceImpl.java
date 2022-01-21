package com.orange.music.service.impl;

import com.orange.music.dao.AdminMapper;
import com.orange.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员接口实现类
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    /**
     * 验证账号密码是否正确
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return adminMapper.verifyPassword(username,password)>0;
    }
}
