package com.bugs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bugs.bean.DiscList;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class RequestTest {

    /**
     * // 歌单
     * musicu.fcg
     * https://u.y.qq.com/cgi-bin/musicu.fcg?-=recom45390136807495907&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data={"comm":{"ct":24},"recomPlaylist":{"method":"get_hot_recommend","param":{"async":1,"cmd":2},"module":"playlist.HotRecommendServer"}}
     */
    @Test
    public void test_() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder("https://u.y.qq.com/cgi-bin/musicu.fcg");
        uriBuilder.setParameter("-", "recom45390136807495907");
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
        //	"comm": {
        //	"ct": 24
        //	},
        //		"recomPlaylist": {
        //		"method": "get_hot_recommend",
        //		"param": {
        //		"async": 1,
        //		"cmd": 2
        //	},
        //	"module": "playlist.HotRecommendServer"
        //	}
        //}
        String data = "{\"comm\":{\"ct\":24},\"recomPlaylist\":{\"method\":\"get_hot_recommend\",\"param\":{\"async\":1,\"cmd\":2},\"module\":\"playlist.HotRecommendServer\"}}";
        JSONObject jsonData = JSON.parseObject(data);
        uriBuilder.setParameter("data", JSON.toJSONString(jsonData));
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity());

            JSONObject temp = JSON.parseObject(content);
            JSONArray jsonArr = temp.getJSONObject("recomPlaylist").getJSONObject("data").getJSONArray("v_hot");
            DiscList[] discLists = new DiscList[jsonArr.size()];
            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                discLists[i] = new DiscList(obj.getString("cover"),
                        obj.getString("username"),
                        obj.getString("title"),
                        obj.getString("content_id"));


            }

            Map<String, Object> resMap = new HashMap<>();
            resMap.put("data", discLists);
            resMap.put("code", temp.get("code"));
            System.out.println(content);
        }
    }





}

