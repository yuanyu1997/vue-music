package com.bugs.service;


import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

public interface SongService {
    /**
     * 获取歌词
     */
    String getLyric(@PathVariable String songId);


    /**
     * 歌单详情页数据
     */
    Map<String, Object> getSongDetailList();
}
