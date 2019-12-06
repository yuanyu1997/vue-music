package com.bugs.controller;

import com.bugs.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    SongService songService;

    /**
     * 抓取歌词
     * http://localhost:8848/song/lyric/001Qu4I30eVFYb
     */
    @RequestMapping("/lyric/{songId}")
    public Map<String, Object> getLyric(@PathVariable String songId){
        Object lyric = songService.getLyric(songId);
        Map<String, Object> resMap = new HashMap<>();
        if (lyric == null) {
            resMap.put("code", -1);
        } else {
            resMap.put("code", 0);
            resMap.put("lyric", lyric);
        }
        return resMap;
    }



    /**
     * 歌单详情页数据
     */
    @RequestMapping("/songDetailList")
    public Map<String, Object> getSongDetailList()  {
        Map<String, Object> resMap = songService.getSongDetailList();
        if (resMap == null) {
            resMap = new HashMap<>();
            resMap.put("code", -1);
        } else {
            resMap.put("code", 0);
        }
        return resMap;
    }

}
