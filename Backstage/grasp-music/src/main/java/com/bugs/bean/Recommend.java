package com.bugs.bean;

/**
 * 返回轮播图所需要的信息
 */
public class Recommend {
    /**
     * 图片链接
     */
    private String imgUrl;
    /**
     * 图片跳转链接
     */
    private String linkUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public Recommend(String linkUrl, String imgUrl) {
        this.linkUrl = linkUrl;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "{" +
                "linkUrl='" + linkUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
