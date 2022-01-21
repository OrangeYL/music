package com.orange.music.service;

import com.orange.music.domain.Consumer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前端用户service接口
 */
public interface ConsumerService {
    /**
     * 新增用户
     * @param consumer
     * @return
     */
    public boolean insert(Consumer consumer);

    /**
     * 修改用户
     * @param consumer
     * @return
     */
    public boolean update(Consumer consumer);

    /**
     * 根据主键id删除用户
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据主键id查询用户
     * @param id
     * @return
     */
    public Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询全部用户
     * @return
     */
    public List<Consumer> selectAll();

    /**
     * 验证密码
     * @param username
     * @param password
     * @return
     */
    public boolean verifyPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    public Consumer selectByUsername(String username);
}
