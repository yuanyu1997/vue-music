package com.bugs.controller;

import com.bugs.bean.SimilarSinger;
import com.bugs.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 歌手 musicu.fcg
 * https://u.y.qq.com/cgi-bin/musicu.fcg?-=getUCGI9588329288102504&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data={"comm":{"ct":24,"cv":0},"singerList":{"module":"Music.SingerListServer","method":"get_singer_list","param":{"area":-100,"sex":-100,"genre":-100,"index":-100,"sin":0,"cur_page":1}}}
 */
@RestController
@CrossOrigin
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    SingerService singerService;

    /**
     * 获取歌手列表
     * 热门歌手 A B ...
     */
    @RequestMapping("/list")
    public Map<String, Object> getSingerList() throws IOException, URISyntaxException {
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
