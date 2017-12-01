package com.qf.kafkaestest.constant;

/**
 * @ClassName: KafkaEnum
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 17:14
 * @Copyright: 2017 . All rights reserved.
 */
public enum KafkaEnum {
    innerTreeModels("innerTreeModels"),PROCESSES("processes"),RULEMODELS("ruleModels"),TREEMODELS("treeModels");
    String name;
    KafkaEnum(String name) {
        this.name = name;
    }
}
