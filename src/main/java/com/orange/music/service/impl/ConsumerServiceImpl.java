package com.orange.music.service.impl;

import com.orange.music.dao.ConsumerMapper;
import com.orange.music.domain.Consumer;
import com.orange.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前端用户service接口
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;
    /**
     * 新增用户
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer)>0;
    }

    /**
     * 修改用户
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.update(consumer)>0;
    }

    /**
     * 根据主键id删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return consumerMapper.delete(id)>0;
    }

    /**
     * 根据主键id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部用户
     *
     * @return
     */
    @Override
    public List<Consumer> selectAll() {
        return consumerMapper.selectAll();
    }

    /**
     * 验证密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return consumerMapper.verifyPassword(username, password)>0;
    }

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    @Override
    public Consumer selectByUsername(String username) {
        return consumerMapper.selectByUsername(username);
    }
}
