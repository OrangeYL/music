package com.orange.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.music.domain.SongList;
import com.orange.music.service.SongListService;
import com.orange.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.insert(songList);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 修改歌单
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String title = request.getParameter("title").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();
        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.update(songList);
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
     * 根据主键id删除歌单
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        boolean flag = songListService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键id查询歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
         return songListService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询全部歌单
     *
     * @return
     */
    @GetMapping("/allSongList")
    public Object selectAll() {
        return songListService.selectAll();
    }

    /**
     * 根据标题模糊搜索歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/likeTitle",method = RequestMethod.GET)
    public Object selectByName(HttpServletRequest request) {
        String title = request.getParameter("title").trim();
        return songListService.selectByTitle(title);
    }
    /**
     * 根据风格模糊搜索歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/likeStyle",method = RequestMethod.GET)
    public Object selectByStyle(HttpServletRequest request) {
        String style = request.getParameter("style").trim();
        return songListService.selectByStyle(style);
    }

    /**
     * 更新歌单图片
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongListPic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile file, @RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(file.isEmpty()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songListPic";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatarPath = "/img/songListPic/"+fileName;
        try {
            file.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatarPath);
            boolean flag = songListService.update(songList);
            if(flag){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"上传成功");
                jsonObject.put("pic",storeAvatarPath);
                return jsonObject;
            }
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }
}
