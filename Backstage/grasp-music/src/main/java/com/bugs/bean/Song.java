package com.bugs.bean;

/**
 * 歌曲
 */
public class Song {
    private String id;

    // 歌曲id
    private String mid;

    //歌手
    private String singer;


    // 歌曲名称
    private String name;

    //专辑名称
    private String album;

    //歌曲长度
    private String duration;

    //歌曲图片
    private String image;

    //歌曲的播放地址
    private String url;

    public Song() {
    }

    public Song(String id, String mid, String singer, String name, String album, String duration, String image, String url) {
        this.id = id;
        this.mid = mid;
        this.singer = singer;
        this.name = name;
        this.album = album;
        this.duration = duration;
        this.image = image;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
