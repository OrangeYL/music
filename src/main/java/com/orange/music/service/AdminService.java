package com.orange.music.service;

/**
 * 管理员Service接口
 */
public interface AdminService {
    /**
     * 验证账号密码是否正确
     * @param username
     * @param password
     * @return
     */
    public boolean verifyPassword(String username,String password);

}
