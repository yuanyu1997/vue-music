package com.bugs.bean;


public class Singer {
    //歌手id
    private String id;
    //歌手姓名
    private String name;
    //歌手头像
    private String avatar;
    //姓名(拼音)所对应的第一个字符小写
    private Character firstCharName;

    public Singer(String id, String name, String avatar, Character firstCharName) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.firstCharName = firstCharName;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", firstCharName=" + firstCharName +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Character getFirstCharName() {
        return firstCharName;
    }

    public void setFirstCharName(Character firstCharName) {
        this.firstCharName = firstCharName;
    }
}
