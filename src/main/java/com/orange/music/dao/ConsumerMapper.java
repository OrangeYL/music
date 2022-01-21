package com.orange.music.dao;

import com.orange.music.domain.Consumer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户mapper接口
 */
@Repository
public interface ConsumerMapper {

    /**
     * 新增用户
     * @param consumer
     * @return
     */
   public int insert(@Param("consumer") Consumer consumer);

    /**
     * 修改用户
     * @param consumer
     * @return
     */
   public int update(@Param("consumer") Consumer consumer);

    /**
     * 根据主键id删除用户
     * @param id
     * @return
     */
   public int delete(Integer id);

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
   public int verifyPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
   public Consumer selectByUsername(String username);


}
