package com.qf.kafkaestest.constant;

/**
 * @ClassName: EsEnum
 * @Description:
 * @author: zhbo
 * @date: 2017/12/1 18:13
 * @Copyright: 2017 . All rights reserved.
 */
public enum EsEnum {
    PROCESS("processes"),MODELS("models");
    public String name;
    EsEnum(String name){
        this.name = name;
    }
}
