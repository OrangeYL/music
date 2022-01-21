package com.orange.music.service.impl;

import com.orange.music.dao.SingerMapper;
import com.orange.music.domain.Singer;
import com.orange.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手service实现类
 */

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;
    /**
     * 新增歌手
     *
     * @param singer
     * @return
     */
    @Override
    public boolean insert(Singer singer) {
        return singerMapper.insert(singer)>0;
    }

    /**
     * 修改歌手
     *
     * @param singer
     * @return
     */
    @Override
    public boolean update(Singer singer) {
        return singerMapper.update(singer)>0;
    }

    /**
     * 根据主键id删除歌手
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return singerMapper.delete(id)>0;
    }

    /**
     * 根据主键id查询歌手
     *
     * @param id
     * @return
     */
    @Override
    public Singer selectByPrimaryKey(Integer id) {
        return singerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部歌手
     *
     * @return
     */
    @Override
    public List<Singer> selectAll() {
        return singerMapper.selectAll();
    }

    /**
     * 根据姓名模糊搜索歌手
     *
     * @param name
     * @return
     */
    @Override
    public List<Singer> selectByName(String name) {
        return singerMapper.selectByName(name);
    }

    /**
     * 根据性别查询歌手
     *
     * @param sex
     * @return
     */
    @Override
    public List<Singer> selectBySex(Integer sex) {
        return singerMapper.selectBySex(sex);
    }
}
