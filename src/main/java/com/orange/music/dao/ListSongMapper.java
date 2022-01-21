package com.orange.music.dao;


import com.orange.music.domain.ListSong;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单歌曲mapper接口
 */
@Repository
public interface ListSongMapper {

    /**
     * 新增歌曲
     * @param listSong
     * @return
     */
   public int insert(@Param("listSong")ListSong listSong);

    /**
     * 修改歌曲
     * @param listSong
     * @return
     */
   public int update(@Param("listSong") ListSong listSong);

    /**
     * 根据主键id删除歌曲
     * @param id
     * @return
     */
   public int delete(Integer id);

    /**
     * 根据歌曲id和歌单id删除歌曲
     * @param songId
     * @param songListId
     * @return
     */
   public int deleteBySongIdAndSongListId(@Param("songId") Integer songId,@Param("songListId") Integer songListId);

    /**
     * 根据主键id查询歌曲
     * @param id
     * @return
     */
   public ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询歌单里全部歌曲
     * @return
     */
   public List<ListSong> selectAll();

    /**
     * 根据歌单id查询歌曲
     * @param songListId
     * @return
     */
   public List<ListSong> selectBySongListId(Integer songListId);
}
