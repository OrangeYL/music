package com.orange.music.service.impl;

import com.orange.music.dao.SongMapper;
import com.orange.music.domain.Song;
import com.orange.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌曲service实现类
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    /**
     * 新增歌曲
     *
     * @param song
     * @return
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song)>0;
    }

    /**
     * 修改歌曲
     *
     * @param song
     * @return
     */
    @Override
    public boolean update(Song song) {
        return songMapper.update(song)>0;
    }

    /**
     * 根据主键id删除歌曲
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id)>0;
    }

    /**
     * 根据主键id查询歌曲
     *
     * @param id
     * @return
     */
    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部歌曲
     *
     * @return
     */
    @Override
    public List<Song> selectAll() {
        return songMapper.selectAll();
    }

    /**
     * 根据姓名模糊搜索歌曲
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> selectByName(String name) {
        return songMapper.selectByName(name);
    }

    /**
     * 根据歌手id查询歌曲
     *
     * @param singerId
     * @return
     */
    @Override
    public List<Song> selectBySingerId(Integer singerId) {
        return songMapper.selectBySingerId(singerId);
    }
}
