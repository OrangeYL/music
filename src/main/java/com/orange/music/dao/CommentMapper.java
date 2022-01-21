package com.orange.music.dao;


import com.orange.music.domain.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论mapper接口
 */
@Repository
public interface CommentMapper {

    /**
     * 新增评论
     * @param comment
     * @return
     */
   public int insert(@Param("comment") Comment comment);

    /**
     * 修改评论
     * @param comment
     * @return
     */
   public int update(@Param("comment") Comment comment);

    /**
     * 根据主键id删除评论
     * @param id
     * @return
     */
   public int delete(Integer id);

    /**
     * 根据主键id查询评论
     * @param id
     * @return
     */
   public Comment selectByPrimaryKey(Integer id);

    /**
     * 查询全部评论
     * @return
     */
   public List<Comment> selectAll();

    /**
     * 根据歌曲id搜索评论
     * @param songId
     * @return
     */
   public List<Comment> selectBySongId(Integer songId);

    /**
     * 根据歌单id搜索评论
     * @param songListId
     * @return
     */
   public List<Comment> selectBySongListId(Integer songListId);
}
