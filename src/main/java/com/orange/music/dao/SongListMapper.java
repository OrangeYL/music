package com.orange.music.dao;


import com.orange.music.domain.SongList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单mapper接口
 */
@Repository
public interface SongListMapper {

    /**
     * 新增歌单
     * @param songList
     * @return
     */
   public int insert(@Param("songList")SongList songList);

    /**
     * 修改歌单
     * @param songList
     * @return
     */
   public int update(@Param("songList") SongList songList);

    /**
     * 根据主键id删除歌单
     * @param id
     * @return
     */
   public int delete(Integer id);

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
