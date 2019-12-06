package com.bugs.controller;

import com.bugs.utils.URIBuilderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;


public class RequestTest {

    URIBuilderUtil uriBuilderUtil = new URIBuilderUtil();


    /**
     * 歌单详情页数据抓取
     */
    @Test
    public void test_03() throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder("https://c.y.qq.com/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg");
        uriBuilder.setParameter("type", "1");
        uriBuilder.setParameter("json", "1");
        uriBuilder.setParameter("utf8", "1");
        uriBuilder.setParameter("onlysong", "0");
        uriBuilder.setParameter("new_format", "1");

        uriBuilder.setParameter("disstid", "7323969373");
        //------
        uriBuilder.setParameter("g_tk", "5381");
        uriBuilder.setParameter("loginUin", "0");
        uriBuilder.setParameter("hostUin", "0");
        uriBuilder.setParameter("format", "json");
        uriBuilder.setParameter("inCharset", "utf8");
        uriBuilder.setParameter("outCharset", "utf-8");
        uriBuilder.setParameter("notice", "0");
        uriBuilder.setParameter("platform", "yqq.json");
        uriBuilder.setParameter("needNewCode", "0");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Origin", "https://y.qq.com");
        httpGet.setHeader("Referer", " https://y.qq.com/n/yqq/playlist/7323969373.html");
        httpGet.setHeader("Sec-Fetch-Mode", "cors");
        httpGet.setHeader("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3887.7 Safari/537.36");
        httpGet.setHeader("Sec-Fetch-Mode", "cors");


        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity());
            System.out.println(content);
        }

    }


}

