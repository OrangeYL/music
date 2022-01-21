package com.orange.music.service.impl;

import com.orange.music.dao.CollectMapper;
import com.orange.music.domain.Collect;
import com.orange.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏service接口实现类
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 新增
     *
     * @param collect
     * @return
     */
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }

    /**
     * 根据主键id删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id)>0;
    }

    /**
     * 删除
     *
     * @param userId
     * @param songId
     * @return
     */
    @Override
    public boolean deleteByUserIdAndSongId(Integer userId, Integer songId) {
        return collectMapper.deleteByUserIdAndSongId(userId,songId)>0;
    }

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Collect> selectAll() {
        return collectMapper.selectAll();
    }

    /**
     * 查询用户的收藏列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Collect> selectByUserId(Integer userId) {
        return collectMapper.selectByUserId(userId);
    }

    /**
     * 查询用户是否收藏过歌曲
     *
     * @param userId
     * @param songId
     * @return
     */
    @Override
    public boolean existCollect(Integer userId, Integer songId) {
        return collectMapper.existCollect(userId, songId)>0;
    }
}
