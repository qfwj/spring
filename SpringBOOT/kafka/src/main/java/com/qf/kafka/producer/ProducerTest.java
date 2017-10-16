/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ProducerTest
 * @Description:
 * @author: zhbo
 * @date: 2017/10/3 21:30
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
public class ProducerTest {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "producer",method = RequestMethod.GET)
    @ResponseBody
    public  String producerTest(String topic, String key, String message){
        ListenableFuture re;
        if(key != null && !key.equals("")){
           re =  kafkaTemplate.send(topic, key, message);
        } else
            re =  kafkaTemplate.send(topic, message);
        return "producer";

    }

}

