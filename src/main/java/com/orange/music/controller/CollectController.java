package com.orange.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.music.domain.Collect;
import com.orange.music.service.CollectService;
import com.orange.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 添加评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        if(songId==null||"".equals(songId)){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"收藏的歌曲为空");
            return jsonObject;
        }
        if(collectService.existCollect(Integer.parseInt(userId),Integer.parseInt(songId))){
            jsonObject.put(Const.CODE,2);
            jsonObject.put(Const.MSG,"已收藏");
            return jsonObject;
        }
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(songId));
        boolean flag = collectService.insert(collect);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"收藏失败");
        return jsonObject;
    }

    /**
     * 根据主键id删除
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String songId = request.getParameter("songId");
        boolean flag = collectService.deleteByUserIdAndSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        return flag;
    }


    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping("/allCollect")
    public Object selectAll() {
        return collectService.selectAll();
    }

    /**
     * 查询用户的收藏列表
     */
    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfSongId(HttpServletRequest request){
        String userId = request.getParameter("userId");
        return collectService.selectByUserId(Integer.parseInt(userId));
    }


}
