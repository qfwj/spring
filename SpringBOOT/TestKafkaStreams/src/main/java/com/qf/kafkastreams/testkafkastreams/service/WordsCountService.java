/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.kafkastreams.testkafkastreams.service;

import com.qf.kafkastreams.testkafkastreams.mapper.TestMapper;
import com.qf.kafkastreams.testkafkastreams.processor.TestProcessor;
import com.qf.kafkastreams.testkafkastreams.transformer.TestTransformer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName: WordsCountService
 * @Description:
 * @author: zhbo
 * @date: 2017/12/12 19:11
 * @Copyright: 2017 . All rights reserved.
 */
public class WordsCountService {
    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.30.130:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();

       /*这个是直来直去的
       *
       * */

       //builder.stream("input-topic").to("output-topic");


       /*这个是使用ValueMapper的apply进行处理
       * 目前只针对业务做相应的转换
       * */
/*        KStream<String, String> textLines = builder.stream("test");
        KStream<String, Object> wordCounts = textLines
                .flatMapValues(new ValueMapper<String, Iterable<String>>() {
          public  Iterable<String> apply(String value) {
              System.out.println(value);
              return  Arrays.asList(value.replace(",","@#$"));
            }
        });
        wordCounts = textLines.flatMapValues( new TestMapper());
        wordCounts.to("testlogstash");
        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();*/


        /*使用transform来进行处理
        *
        *
        *
        * */

        KStream<String, String> textLines1 = builder.stream("test");
        textLines1 =  textLines1.transformValues(new ValueTransformerSupplier<String, String>(){
            public ValueTransformer get(){
                return new TestTransformer();
            }
        } );
        textLines1.to("testlogstash");
        Topology topology = builder.build();
        System.out.println(topology.describe());
        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
    }
}
