package com.bugs.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bugs.bean.Recommend;
import org.junit.Test;

public class FastJsonTest {

    @Test
    public void data() {

        String data = "{\"focus\":{\"data\":{\"content\":[{\"cover\":\"\",\"id\":24392,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"https://y.qq.com/msa/270/1_8917.html?openinqqmusic=1&ADTAG=pc\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1874994.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":3002},{\"cover\":\"\",\"id\":24366,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"001b3j8n1oPYnt\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1875572.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10002},{\"cover\":\"\",\"id\":24291,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"003tYO9f4JE3PN\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1870928.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10002},{\"cover\":\"\",\"id\":24379,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"00321pOk0eOFks\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1874098.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10002},{\"cover\":\"\",\"id\":24382,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"001FUHas3mEGTk\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1873805.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10002},{\"cover\":\"\",\"id\":24357,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"003zqZni4NnS2H\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1875611.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10002},{\"cover\":\"\",\"id\":24375,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"7289821203\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1875816.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10014},{\"cover\":\"\",\"id\":24380,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"000OzqkG3PTxG5\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1873352.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10002},{\"cover\":\"\",\"id\":24369,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"000xZ0Uz3b8od0\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1873959.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10002},{\"cover\":\"\",\"id\":24348,\"jump_info\":{\"id\":0,\"mid\":\"\",\"url\":\"004PhrzH0TVpor\"},\"listen_num\":0,\"pic_info\":{\"mid\":\"\",\"url\":\"http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1871471.jpg\",\"urlex1\":\"\",\"urlex2\":\"\"},\"report\":\"\",\"sub_title\":\"\",\"title\":\"\",\"type\":10002}],\"id\":1,\"sub_cube\":[],\"title\":\"焦点图\"},\"code\":0},\"code\":0,\"ts\":1575107052676}";

        JSONObject jsonObject = JSON.parseObject(data);
        JSONArray jsonArray = jsonObject.getJSONObject("focus").getJSONObject("data").getJSONArray("content");
        Recommend[] reArr = new Recommend[jsonArray.size()];
        for (int i = 1; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String imgUrl = obj.getJSONObject("pic_info").get("url").toString();
            String jumpUrlKey = obj.getJSONObject("jump_info").get("url").toString();
            String url = "https://y.qq.com/n/yqq/album/" + jumpUrlKey + ".html";
            System.out.println(url);
            System.out.println(imgUrl);
            System.out.println();
        }
    }
}
//http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1874994.jpg
//https://y.qq.com/msa/270/1_8917.html?openinqqmusic=1&ADTAG=pc