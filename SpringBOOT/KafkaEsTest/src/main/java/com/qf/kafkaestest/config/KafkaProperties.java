/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.kafkaestest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: KafkaProperties
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 22:25
 * @Copyright: 2017 . All rights reserved.
 */
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties{

    private String broker;
    private String topic;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private String group;

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

