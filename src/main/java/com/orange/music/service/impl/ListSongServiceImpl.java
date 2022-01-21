package com.orange.music.service.impl;

import com.orange.music.dao.ListSongMapper;
import com.orange.music.domain.ListSong;
import com.orange.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单歌曲service接口实现类
 */
@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;
    /**
     * 新增歌曲
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong)>0;
    }

    /**
     * 修改歌曲
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong)>0;
    }

    /**
     * 根据主键id删除歌曲
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return listSongMapper.delete(id)>0;
    }

    /**
     * 根据歌曲id和歌单id删除歌曲
     *
     * @param songId
     * @param songListId
     * @return
     */
    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId) {
        return listSongMapper.deleteBySongIdAndSongListId(songId,songListId)>0;
    }

    /**
     * 根据主键id查询歌曲
     *
     * @param id
     * @return
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询歌单里全部歌曲
     *
     * @return
     */
    @Override
    public List<ListSong> selectAll() {
        return listSongMapper.selectAll();
    }

    /**
     * 根据歌单id查询歌曲
     *
     * @param songListId
     * @return
     */
    @Override
    public List<ListSong> selectBySongListId(Integer songListId) {
        return listSongMapper.selectBySongListId(songListId);
    }
}
