package com.orange.music.service.impl;

import com.orange.music.dao.SongListMapper;
import com.orange.music.domain.SongList;
import com.orange.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单service实现类
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    /**
     * 新增歌单
     *
     * @param songList
     * @return
     */
    @Override
    public boolean insert(SongList songList) {
        return songListMapper.insert(songList)>0;
    }

    /**
     * 修改歌单
     *
     * @param songList
     * @return
     */
    @Override
    public boolean update(SongList songList) {
        return songListMapper.update(songList)>0;
    }

    /**
     * 根据主键id删除歌单
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songListMapper.delete(id)>0;
    }

    /**
     * 根据主键id查询歌单
     *
     * @param id
     * @return
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部歌单
     *
     * @return
     */
    @Override
    public List<SongList> selectAll() {
        return songListMapper.selectAll();
    }

    /**
     * 根据标题模糊搜索歌单
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> selectByTitle(String title) {
        return songListMapper.selectByTitle(title);
    }

    /**
     * 根据风格查询歌单
     *
     * @param style
     * @return
     */
    @Override
    public List<SongList> selectByStyle(String style) {
        return songListMapper.selectByStyle(style);
    }
}
