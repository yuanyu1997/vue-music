package com.bugs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RequestTest {
//    @Autowired
//    PoolingHttpClientConnectionManager manager;

    //System.out.println(httpGet);
    //httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
    //httpGet.setHeader("Origin", "https://y.qq.com");
    //httpGet.setHeader("Referer", "https://y.qq.com/");
    //httpGet.setHeader("Sec-Fetch-Mode", "cors");
    //httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3887.7 Safari/537.36");

    @Test
    public void test_post() throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

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
        JSONObject jsonObject = JSON.parseObject(data);
        uriBuilder.setParameter("data", JSON.toJSONString(jsonObject));

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //System.out.println(httpGet);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity());
            System.out.println(content);
        }





    }


}
