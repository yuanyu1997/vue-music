package com.bugs.service;


import java.util.Map;


public interface RecommendService {
    /**
     * 轮播图
     */
    Map<String, Object> getRecommend();

    /**
     * 歌单
     */
    Map<String, Object> getDiscList();
}
