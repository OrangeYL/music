package com.orange.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.music.domain.Consumer;
import com.orange.music.service.ConsumerService;
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
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加前端用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        String avator = request.getParameter("avator").trim();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(username==null||"".equals(username)){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||"".equals(password)){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"密码不能为空");
            return jsonObject;
        }
        Consumer consumer1 = consumerService.selectByUsername(username);
        if(consumer1!=null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"用户名已存在");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        boolean flag = consumerService.insert(consumer);
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
     * 修改前端用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        boolean flag = consumerService.update(consumer);
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
     * 根据主键id删除前端用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        boolean flag = consumerService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键id查询前端用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
         return consumerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询全部前端用户
     *
     * @return
     */
    @GetMapping("/allConsumer")
    public Object selectAll() {
        return consumerService.selectAll();
    }

    /**
     * 根据账号查询前端用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/consumerOfUsername",method = RequestMethod.GET)
    public Consumer selectBySex(HttpServletRequest request) {
        String username = request.getParameter("username").trim();
        return consumerService.selectByUsername(username);
    }

    /**
     * 更新前端用户图片
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateConsumerPic",method = RequestMethod.POST)
    public Object updateConsumerPic(@RequestParam("file") MultipartFile file, @RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(file.isEmpty()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"avatorImages";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatarPath = "/avatorImages/"+fileName;
        try {
            file.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatarPath);
            boolean flag = consumerService.update(consumer);
            if(flag){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"上传成功");
                jsonObject.put("avator",storeAvatarPath);
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

    /**
     * 前端用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if(username==null||"".equals(username)){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||"".equals(password)){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"密码不能为空");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        boolean flag = consumerService.verifyPassword(username, password);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"登录成功");
            jsonObject.put("userMsg",consumerService.selectByUsername(username));
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"登录失败");
        return jsonObject;
    }
}
