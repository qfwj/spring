/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.kafkaestest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: EsProperties
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 22:24
 * @Copyright: 2017 . All rights reserved.
 */
@ConfigurationProperties(prefix = "es")
public class EsProperties {
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

