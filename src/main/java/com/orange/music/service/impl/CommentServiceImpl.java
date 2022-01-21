package com.orange.music.service.impl;

import com.orange.music.dao.CommentMapper;
import com.orange.music.domain.Comment;
import com.orange.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论service接口实现类
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment)>0;
    }

    /**
     * 修改评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean update(Comment comment) {
        return commentMapper.update(comment)>0;
    }

    /**
     * 根据主键id删除评论
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return commentMapper.delete(id)>0;
    }

    /**
     * 根据主键id查询评论
     *
     * @param id
     * @return
     */
    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部评论
     *
     * @return
     */
    @Override
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }

    /**
     * 根据歌曲id搜索评论
     *
     * @param songId
     * @return
     */
    @Override
    public List<Comment> selectBySongId(Integer songId) {
        return commentMapper.selectBySongId(songId);
    }

    /**
     * 根据歌单id搜索评论
     *
     * @param songListId
     * @return
     */
    @Override
    public List<Comment> selectBySongListId(Integer songListId) {
        return commentMapper.selectBySongListId(songListId);
    }
}
