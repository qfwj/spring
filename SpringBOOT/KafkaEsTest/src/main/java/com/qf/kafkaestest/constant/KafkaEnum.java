package com.qf.kafkaestest.constant;

/**
 * @ClassName: KafkaEnum
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 17:14
 * @Copyright: 2017 . All rights reserved.
 */
public enum KafkaEnum {
    GBDTMODELS("gbdtModels"), PROCESSES("processes"), RULEMODELS("ruleModels"), TREEMODELS("treeModels"), PYTHONMODELS("pythonModels");
    public String strName;

    KafkaEnum(String strName) {
        this.strName = strName;
    }
}
