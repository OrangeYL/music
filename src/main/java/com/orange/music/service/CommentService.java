package com.orange.music.service;

import com.orange.music.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论service接口
 */
public interface CommentService {

    /**
     * 新增评论
     * @param comment
     * @return
     */
    public boolean insert(@Param("comment") Comment comment);

    /**
     * 修改评论
     * @param comment
     * @return
     */
    public boolean update(@Param("comment") Comment comment);

    /**
     * 根据主键id删除评论
     * @param id
     * @return
     */
    public boolean delete(Integer id);

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
     * @param SongId
     * @return
     */
    public List<Comment> selectBySongId(Integer songId);

    /**
     * 根据歌单id搜索评论
     * @param SongListId
     * @return
     */
    public List<Comment> selectBySongListId(Integer songListId);
}
