package com.orange.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.music.domain.Rank;
import com.orange.music.service.RankService;
import com.orange.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService rankService;

    /**
     * 新增评价
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songListId = request.getParameter("songListId");
        String consumerId = request.getParameter("consumerId");
        String score = request.getParameter("score");
        Rank rank = new Rank();
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));
        boolean flag = rankService.insert(rank);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"评价成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"评价失败");
        return jsonObject;
    }
    @GetMapping
    public Object AverageScore(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return rankService.AverageScore(Integer.parseInt(songListId));
    }
}
