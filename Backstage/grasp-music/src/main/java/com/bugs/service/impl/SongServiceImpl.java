package com.bugs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bugs.service.SongService;
import com.bugs.utils.URIBuilderUtil;
import com.bugs.utils.httpGetUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    PoolingHttpClientConnectionManager manager;
    @Autowired
    URIBuilderUtil uriBuilderUtil;
    @Autowired
    httpGetUtil httpGetUtil;

    @Override
    public String getLyric(@PathVariable String songId) {
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();
            URIBuilder uriBuilder = new URIBuilder("https://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric_new.fcg");
            uriBuilder.setParameter("-", "MusicJsonCallback_lrc");
            uriBuilder.setParameter("pcachetime", "1575633616642");
            uriBuilder.setParameter("songmid", songId);
            uriBuilderUtil.setBasicParameter(uriBuilder);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGetUtil.setBasicHeader(httpGet);
            httpGet.setHeader("Origin", "https://y.qq.com");
            httpGet.setHeader("Referer", " https://y.qq.com/portal/player.html");
            httpGet.setHeader("Sec-Fetch-Mode", "cors");
            httpGet.setHeader("Sec-Fetch-Mode", "cors");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity());
                JSONObject jsonObj = JSON.parseObject(content);
                return jsonObj.getString("lyric");
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    /**
     * 歌单详情页数据
     */
    @Override
    public Map<String, Object> getSongDetailList() {
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();
            URIBuilder uriBuilder = new URIBuilder("https://c.y.qq.com/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg");
            uriBuilder.setParameter("type", "1");
            uriBuilder.setParameter("json", "1");
            uriBuilder.setParameter("utf8", "1");
            uriBuilder.setParameter("onlysong", "0");
            uriBuilder.setParameter("new_format", "1");
            uriBuilder.setParameter("disstid", "7323969373");
            uriBuilderUtil.setBasicParameter(uriBuilder);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGetUtil.setBasicHeader(httpGet);
            httpGet.setHeader("Origin", "https://y.qq.com");
            httpGet.setHeader("Referer", " https://y.qq.com/n/yqq/playlist/7323969373.html");
            httpGet.setHeader("Sec-Fetch-Mode", "cors");
            httpGet.setHeader("Sec-Fetch-Mode", "cors");

            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(content);
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }


}
