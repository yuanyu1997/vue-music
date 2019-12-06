package com.bugs.service;

import com.bugs.bean.SimilarSinger;

import java.util.ArrayList;


public interface SingerService {
    /**
     * "key"一致的歌手(首字母相同|歌手)
     */
    ArrayList<SimilarSinger> getSingerList();
}
