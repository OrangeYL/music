package com.orange.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.music.domain.Comment;
import com.orange.music.service.CommentService;
import com.orange.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        String content = request.getParameter("content");
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(new Byte(type)==0){
            comment.setSongId(Integer.parseInt(songId));
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"评论成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"评论失败");
        return jsonObject;
    }

    /**
     * 修改评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");          //主键
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        String content = request.getParameter("content");
        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(songId!=null&&songId.equals("")){
            songId = null;
        }else {
            comment.setSongId(Integer.parseInt(songId));
        }
        if(songListId!=null&&songListId.equals("")){
            songListId = null;
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);

        boolean flag = commentService.update(comment);
        if(flag){   //保存成功
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 根据主键id删除评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        String id = request.getParameter("id");
        boolean flag = commentService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键id查询评论
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id");
         return commentService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询全部评论
     *
     * @return
     */
    @GetMapping("/allComment")
    public Object selectAll() {
        return commentService.selectAll();
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @RequestMapping(value = "/commentOfSongId",method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId");          //歌曲id
        return commentService.selectBySongId(Integer.parseInt(songId));
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @RequestMapping(value = "/commentOfSongListId",method = RequestMethod.GET)
    public Object commentOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");          //歌曲id
        return commentService.selectBySongListId(Integer.parseInt(songListId));
    }

    /**
     * 给某个评论点赞
     */
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public Object like(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");           //主键
        String up = request.getParameter("up");           //用户id

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean flag = commentService.update(comment);
        if(flag){   //保存成功
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"点赞成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"点赞失败");
        return jsonObject;
    }
}
