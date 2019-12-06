package com.bugs.service;

import com.bugs.bean.SimilarSinger;
import com.bugs.bean.Song;

import java.util.ArrayList;
import java.util.List;


public interface SingerService {
    /**
     * "key"一致的歌手(首字母相同|歌手)
     */
    ArrayList<SimilarSinger> getSingerList();

    /**
     * 具体某一个歌手歌曲列表
     */
    List<Song> getSingerDetail(String singerId);
}
