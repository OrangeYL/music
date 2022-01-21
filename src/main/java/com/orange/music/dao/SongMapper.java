package com.orange.music.dao;


import com.orange.music.domain.Song;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲mapper接口
 */
@Repository
public interface SongMapper {

    /**
     * 新增歌曲
     * @param song
     * @return
     */
   public int insert(@Param("song")Song song);

    /**
     * 修改歌曲
     * @param song
     * @return
     */
   public int update(@Param("song") Song song);

    /**
     * 根据主键id删除歌曲
     * @param id
     * @return
     */
   public int delete(Integer id);

    /**
     * 根据主键id查询歌曲
     * @param id
     * @return
     */
   public Song selectByPrimaryKey(Integer id);

    /**
     * 查询全部歌曲
     * @return
     */
   public List<Song> selectAll();

    /**
     * 根据姓名模糊搜索歌曲
     * @param name
     * @return
     */
   public List<Song> selectByName(String name);

    /**
     * 根据歌手id查询歌曲
     * @param singerId
     * @return
     */
   public List<Song> selectBySingerId(Integer singerId);
}
