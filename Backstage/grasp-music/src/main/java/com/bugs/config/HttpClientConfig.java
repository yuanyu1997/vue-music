package com.bugs.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    /**
     * 连接池
     */
    @Bean
    public PoolingHttpClientConnectionManager connectionManager() {
        //创建连接池管理器
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        //设置最大的连接数
        manager.setMaxTotal(100);
        // 设置每个主机的最大连接数（每个主机的并发数）
        manager.setDefaultMaxPerRoute(20);
        return manager;
    }
}
