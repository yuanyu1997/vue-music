package com.bugs.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bugs.bean.Recommend;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecommendService {
    @Autowired
    PoolingHttpClientConnectionManager manager;

    public Map<String, Object> getRecommend() throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();
        URIBuilder uriBuilder = new URIBuilder("https://u.y.qq.com/cgi-bin/musicu.fcg");
        uriBuilder.setParameter("g_tk", "5381");
        uriBuilder.setParameter("loginUin", "0");
        uriBuilder.setParameter("hostUin", "0");
        uriBuilder.setParameter("format", "json");
        uriBuilder.setParameter("inCharset", "utf8");
        uriBuilder.setParameter("outCharset", "utf-8");
        uriBuilder.setParameter("notice", "0");
        uriBuilder.setParameter("platform", "yqq.json");
        uriBuilder.setParameter("needNewCode", "0");
        //{
        //	"focus": {
        //		"module": "QQMusic.MusichallServer",
        //		"method": "GetFocus",
        //		"param": {}
        //	}
        //}
        String data = "{\"focus\":{\"module\":\"QQMusic.MusichallServer\",\"method\":\"GetFocus\",\"param\":{}}}";
        JSONObject jsonData = JSON.parseObject(data);
        uriBuilder.setParameter("data", JSON.toJSONString(jsonData));

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity());
            JSONObject temp = JSON.parseObject(content);
            JSONArray jsonArray = temp.getJSONObject("focus").getJSONObject("data").getJSONArray("content");
            Recommend[] reArr = new Recommend[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String picUrl = obj.getJSONObject("pic_info").get("url").toString();
                String jumpUrlKey = obj.getJSONObject("jump_info").get("url").toString();
                if (jumpUrlKey.contains("https")) {
                    reArr[i] = new Recommend(jumpUrlKey, picUrl);
                } else {
                    String linkUrl = "https://y.qq.com/n/yqq/album/" + jumpUrlKey + ".html";
                    reArr[i] = new Recommend(linkUrl, picUrl);
                }
            }
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("data", reArr);
            resMap.put("code", temp.get("code"));
            return resMap;
        }
        return null;
    }
}
