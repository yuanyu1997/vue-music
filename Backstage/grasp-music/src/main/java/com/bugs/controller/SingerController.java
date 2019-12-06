package com.bugs.controller;

import com.bugs.bean.SimilarSinger;
import com.bugs.bean.Song;
import com.bugs.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    /**
     * 具体某一个歌手歌曲列表
     *
     * @param singerId 歌手id
     * 薛之谦: 002J4UUk29y8BY
     * http://localhost:8848/singer/detail/002J4UUk29y8BY
     * 演员: 001Qu4I30eVFYb
     */
    @RequestMapping("/detail/{singerId}")
    public Map<String, Object> getSingerDetail(@PathVariable String singerId) {
        List<Song> songList = singerService.getSingerDetail(singerId);
        Map<String, Object> res = new HashMap<>();
        if (songList == null) {
            res.put("code", -1);
        } else {
            res.put("code", 0);
            res.put("data", songList);
        }
        return res;
    }


}
