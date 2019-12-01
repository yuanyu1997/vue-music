package com.bugs.bean;

/**
 * 返回轮播图所需要的信息
 */
public class Recommend {
    /**
     * 图片链接
     */
    private String imgurl;
    /**
     * 图片跳转链接
     */
    private String linkUrl;


    public String getImgurl() {
        return imgurl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public Recommend(String linkUrl, String imgurl) {
        this.linkUrl = linkUrl;
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "{" +
                "linkUrl='" + linkUrl + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}
