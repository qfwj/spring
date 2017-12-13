/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.kafkastreams.testkafkastreams.mapper;

import org.apache.kafka.streams.kstream.ValueMapper;

import java.util.Arrays;

/**
 * @ClassName: TestMapper
 * @Description:
 * @author: zhbo
 * @date: 2017/12/13 23:27
 * @Copyright: 2017 . All rights reserved.
 */
public class TestMapper implements ValueMapper<String, Iterable<String>> {
    @Override
    public Iterable<String> apply(String sourcerecord) {
        System.out.println(sourcerecord);
        return Arrays.asList("output" + sourcerecord);
    }
}

