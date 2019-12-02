package com.bugs.bean;

import java.util.ArrayList;

/**
 * 首字母一致的歌手
 */
public class SimilarSinger {
    // 关键字
    private String key;
    // 首字母一致的歌手
    private ArrayList<Singer> list;


    public SimilarSinger(String key) {
        this.key = key;
        this.list = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SimilarSinger{" +
                "key='" + key + '\'' +
                ", list=" + list +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<Singer> getList() {
        return list;
    }

    public void setList(ArrayList<Singer> list) {
        this.list = list;
    }
}
