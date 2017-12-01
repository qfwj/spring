/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.kafkaestest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qf.kafkaestest.constant.EsConstant;
import com.qf.kafkaestest.constant.EsEnum;
import com.qf.kafkaestest.constant.KafkaEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: KafkaEsTest
 * @Description:
 * @author: zhbo
 * @date: 2017/11/29 17:59
 * @Copyright: 2017 . All rights reserved.
 */
public class KafkaService {
    @Autowired
    private KafkaConsumer kafkaConsumer;
    @Autowired
    private Esservice esservice;

    public String getKafka() {
        Properties props = new Properties();
        props.put("bootstrap.servers", ":9092");
        props.put("group.id", "estest");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList("test"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1008);
            for (ConsumerRecord<String, String> record : records) {
                esservice.saveToEs(this.kafka2Es(record.value()));
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
            }
        }
    }

    private Map<String, String> kafka2Es(String kafkadata) {
        JSONObject json = JSON.parseObject(kafkadata);
        String logtype = json.get("logType").toString();
        Map<String, String> map = new HashMap<>(3);
        if(logtype.equals(KafkaEnum.PROCESSES.name())); //三种ruleModels treeModels processes
        map.put(EsConstant.TYPE, EsEnum.PROCESS.name);

        /*id*/
        /*json.get("type"), json.get("id"))
                    .setSource(json.get("data"), XContentType.JSON)*/

       // json.remove("")
        JSONArray jsonArray = (JSONArray) json.get("input");
        //jsonArray


        return map;
    }

    public static void main(String[] args) {
        new KafkaService().getKafka();
    }
}
