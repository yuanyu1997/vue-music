package com.bugs.bean;

/**
 * 返回轮播图所需要的信息
 */
public class Recommend {
    /**
     * 图片链接
     */
    private String picUrl;
    /**
     * 图片跳转链接
     */
    private String linkUrl;


    public String getLinkUrl() {
        return linkUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Recommend(String linkUrl, String picUrl) {
        this.linkUrl = linkUrl;
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "{" +
                "linkUrl='" + linkUrl + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
