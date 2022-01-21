package com.orange.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.orange.music.domain.ListSong;
import com.orange.music.service.ListSongService;
import com.orange.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 歌单歌曲controller
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    /**
     * 添加歌曲
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //获取前端传来的参数
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();

        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = listSongService.insert(listSong);
        if (flag) {
            jsonObject.put(Const.CODE, 1);
            jsonObject.put(Const.MSG, "保存成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE, 0);
        jsonObject.put(Const.MSG, "保存失败");
        return jsonObject;

    }

    /**
     * 根据歌单id查询歌曲
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object listSongOfSingerId(HttpServletRequest request){
        String songListId = request.getParameter("songListId").trim();
        return listSongService.selectBySongListId(Integer.parseInt(songListId));
    }

    /**
     * 修改歌曲
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();

        //保存到歌手的对象中
        ListSong listSong = new ListSong();
        listSong.setId(Integer.parseInt(id));
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = listSongService.update(listSong);
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
     * 删除歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId");
        boolean flag = listSongService.deleteBySongIdAndSongListId(Integer.parseInt(songId),Integer.parseInt(songListId));
        return flag;
    }

}




















