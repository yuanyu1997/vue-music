package com.bugs.bean;

/**
 * 歌单
 */
public class DiscList {
    //歌单图片
    private String imgurl;
    //歌单名字
    private String name;
    //歌单描述
    private String desc;

    //歌单id
    private String id;
    public DiscList(String imgurl, String name, String desc,String id) {
        this.imgurl = imgurl;
        this.name = name;
        this.desc = desc;
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getId() {
        return id;
    }
}
