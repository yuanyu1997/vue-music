package com.bugs.controller;

import com.bugs.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/recommend")
public class RecommendController extends BaseController{

    @Autowired
    RecommendService recommendService;

    /**
     * 轮播图
     */
    @RequestMapping("/picture")
    public Map<String, Object> getRecommend() {
        Map<String, Object> resMap = recommendService.getRecommend();
        if (resMap == null) {
            resMap = new HashMap<>();
            resMap.put("code", -1);
        } else {
            resMap.put("code", 0);
        }
        return resMap;
    }

    /**
     * 歌单信息
     */
    @RequestMapping("/songList")
    public Map<String, Object> getDiscList()  {
        Map<String, Object> resMap = recommendService.getDiscList();
        if (resMap == null) {
            resMap = new HashMap<>();
            resMap.put("code", -1);
        } else {
            resMap.put("code", 0);
        }
        return resMap;
    }

}
