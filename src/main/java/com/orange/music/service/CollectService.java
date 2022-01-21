package com.orange.music.service;


import com.orange.music.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 收藏service接口
 */
public interface CollectService {
    /**
     * 新增
     * @param collect
     * @return
     */
    public boolean insert(@Param("collect") Collect collect);


    /**
     * 根据主键id删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 删除
     * @param userId
     * @param songId
     * @return
     */
    public boolean deleteByUserIdAndSongId(@Param("userId") Integer userId,@Param("songId") Integer songId);

    /**
     * 查询全部
     * @return
     */
    public List<Collect> selectAll();

    /**
     * 查询用户的收藏列表
     * @param userId
     * @return
     */
    public List<Collect> selectByUserId(Integer userId);

    /**
     * 查询用户是否收藏过歌曲
     * @param userId
     * @param songId
     * @return
     */
    public boolean existCollect(@Param("userId") Integer userId, @Param("songId") Integer songId);

}
