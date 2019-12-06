package com.bugs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class URIBuilderUtil {
    @Autowired
    PoolingHttpClientConnectionManager manager;

    @SafeVarargs
    public final JSONObject getResponseByBasicParameter(String url, String parData, Pair<String, String>... parameter) {
        try {
            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();
            URIBuilder uriBuilder = new URIBuilder(url);
            setBasicParameter(uriBuilder);
            JSONObject jsonData = JSON.parseObject(parData);
            uriBuilder.setParameter("data", JSON.toJSONString(jsonData));
            for (Pair<String, String> pair : parameter) {
                uriBuilder.setParameter(pair.getKey(), pair.getValue());
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity());
                return JSON.parseObject(content);
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 设置请求参数中携带的基本信息
     */
    private void setBasicParameter(URIBuilder uriBuilder) {
        uriBuilder.setParameter("g_tk", "5381");
        uriBuilder.setParameter("loginUin", "0");
        uriBuilder.setParameter("hostUin", "0");
        uriBuilder.setParameter("format", "json");
        uriBuilder.setParameter("inCharset", "utf8");
        uriBuilder.setParameter("outCharset", "utf-8");
        uriBuilder.setParameter("notice", "0");
        uriBuilder.setParameter("platform", "yqq.json");
        uriBuilder.setParameter("needNewCode", "0");
    }
}
