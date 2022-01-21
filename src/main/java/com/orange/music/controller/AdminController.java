package com.orange.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.music.service.AdminService;
import com.orange.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录成功
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean flag = adminService.verifyPassword(name, password);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"登陆成功");
            session.setAttribute(Const.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"用户名或密码错误");
        return jsonObject;
    }
}
