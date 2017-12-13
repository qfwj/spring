/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.kafkastreams.testkafkastreams.transformer;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.StateStore;

/**
 * @ClassName: TestTransformer
 * @Description:
 * @author: zhbo
 * @date: 2017/12/14 0:20
 * @Copyright: 2017 . All rights reserved.
 */
public class TestTransformer implements ValueTransformer<String , String> {
    private ProcessorContext context;
   // private StateStore state;

    public void init(ProcessorContext context) {
        this.context = context;
       // this.state = context.getStateStore("myTransformState");
        // punctuate each 1000ms; can access this.state
        // can emit as many new KeyValue pairs as required via this.context#forward()

    }

    /**
     * @deprecated
     */
    @Deprecated
   public String punctuate(long var1) {
        return null;
    }

    public String transform(String value) {
        System.out.println("old" + value);
        // can access this.state
        // can emit as many new KeyValue pairs as required via this.context#forward()
        return "new" + value; // can emit a single value via return -- can also be null
    }

    public void close() {
        // can access this.state
        // can emit as many new KeyValue pairs as required via this.context#forward()
    }
}

