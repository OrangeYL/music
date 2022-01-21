package com.orange.music.dao;


import com.orange.music.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手mapper接口
 */
@Repository
public interface SingerMapper {

    /**
     * 新增歌手
     * @param singer
     * @return
     */
   public int insert(Singer singer);

    /**
     * 修改歌手
     * @param singer
     * @return
     */
   public int update(Singer singer);

    /**
     * 根据主键id删除歌手
     * @param id
     * @return
     */
   public int delete(Integer id);

    /**
     * 根据主键id查询歌手
     * @param id
     * @return
     */
   public Singer selectByPrimaryKey(Integer id);

    /**
     * 查询全部歌手
     * @return
     */
   public List<Singer> selectAll();

    /**
     * 根据姓名模糊搜索歌手
     * @param name
     * @return
     */
   public List<Singer> selectByName(String name);

    /**
     * 根据性别查询歌手
     * @param sex
     * @return
     */
   public List<Singer> selectBySex(Integer sex);
}
