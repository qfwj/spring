/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.kafkaestest;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: EsConfig
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 14:34
 * @Copyright: 2017 . All rights reserved.
 */
@Configuration
public class EsConfig {
    @Autowired
    private EsProperties esProperties;
    @Bean
    public TransportClient getTransportClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", false)
                .put("cluster.name", esProperties.getClustername())
                .build();

        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esProperties.getHost()), 9300));

        return client;
    }
}


@ConfigurationProperties(prefix = "es")
class EsProperties{
    private String host;
    private String index;
    private String clustername;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getClustername() {
        return clustername;
    }

    public void setClustername(String clustername) {
        this.clustername = clustername;
    }
}