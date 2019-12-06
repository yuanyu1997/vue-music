package com.bugs.utils;

import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Component;

@Component
public class httpGetUtil {
    public void setBasicHeader(HttpGet httpGet){
        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3887.7 Safari/537.36");
    }

}
