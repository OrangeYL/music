package com.orange.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.music.domain.Singer;
import com.orange.music.service.SingerService;
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
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.insert(singer);
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
     * 修改歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String name = request.getParameter("name").trim();      //姓名
        String sex = request.getParameter("sex").trim();        //性别
        String birth = request.getParameter("birth").trim();    //生日
        String location = request.getParameter("location").trim();//地区
        String introduction = request.getParameter("introduction").trim();//简介
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.update(singer);
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
     * 根据主键id删除歌手
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        boolean flag = singerService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键id查询歌手
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
         return singerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询全部歌手
     *
     * @return
     */
    @GetMapping("/allSinger")
    public Object selectAll() {
        return singerService.selectAll();
    }

    /**
     * 根据姓名模糊搜索歌手
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/singerOfName",method = RequestMethod.GET)
    public Object selectByName(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        return singerService.selectByName(name);
    }

    /**
     * 根据性别查询歌手
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/singerOfSex",method = RequestMethod.GET)
    public List<Singer> selectBySex(HttpServletRequest request) {
        String sex = request.getParameter("sex").trim();
        return singerService.selectBySex(Integer.parseInt(sex));
    }

    /**
     * 更新歌手图片
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSingerPic",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile file, @RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(file.isEmpty()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatarPath = "/img/singerPic/"+fileName;
        try {
            file.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatarPath);
            boolean flag = singerService.update(singer);
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
