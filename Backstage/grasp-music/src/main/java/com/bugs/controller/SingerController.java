package com.bugs.controller;

import com.bugs.bean.SimilarSinger;
import com.bugs.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 歌手
 */
@CrossOrigin
@RestController
@RequestMapping("/singer")
public class SingerController extends BaseController {
    @Autowired
    SingerService singerService;

    /**
     * "key"一致的歌手(首字母相同|歌手)
     * 热门歌手 A B ...
     */
    @RequestMapping("/list")
    public Map<String, Object> getSingerList() {
        Map<String, Object> resMap = new HashMap<>();
        ArrayList<SimilarSinger> res = singerService.getSingerList();
        if (res.size() > 0) {
            resMap.put("data", res);
            resMap.put("code", 0);
            return resMap;
        } else {
            resMap.put("code", -1);
            return resMap;
        }
    }
}
