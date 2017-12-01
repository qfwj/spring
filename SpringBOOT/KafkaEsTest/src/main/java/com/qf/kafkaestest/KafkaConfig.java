/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.kafkaestest;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName: KafkaConfig
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 14:34
 * @Copyright: 2017 . All rights reserved.
 */
@Configuration
public class KafkaConfig {
    @Autowired
    private KafkaProperties kafkaProperties;
    @Bean
    public KafkaConsumer getKafkaConsumer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaProperties.getBroker());
        props.put("group.id", kafkaProperties.getGroup());
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList(kafkaProperties.getTopic()));
        return kafkaConsumer;
    }
}

@ConfigurationProperties(prefix = "kafka")
class KafkaProperties{

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