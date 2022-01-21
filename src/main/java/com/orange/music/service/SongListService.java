package com.orange.music.service;

import com.orange.music.domain.SongList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 歌单service接口
 */
public interface SongListService {
    /**
     * 新增歌单
     * @param songList
     * @return
     */
    public boolean insert(@Param("songList")SongList songList);

    /**
     * 修改歌单
     * @param songList
     * @return
     */
    public boolean update(@Param("songList") SongList songList);

    /**
     * 根据主键id删除歌单
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据主键id查询歌单
     * @param id
     * @return
     */
    public SongList selectByPrimaryKey(Integer id);

    /**
     * 查询全部歌单
     * @return
     */
    public List<SongList> selectAll();

    /**
     * 根据标题模糊搜索歌单
     * @param title
     * @return
     */
    public List<SongList> selectByTitle(String title);

    /**
     * 根据风格查询歌单
     * @param style
     * @return
     */
    public List<SongList> selectByStyle(String style);
}
