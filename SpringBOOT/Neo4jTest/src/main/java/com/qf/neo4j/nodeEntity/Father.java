/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.nodeEntity;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @ClassName: Father
 * @Description:
 * @author: zhbo
 * @date: 2017/10/30 17:46
 * @Copyright: 2017 . All rights reserved.
 */
@NodeEntity(label = "father")
public class Father extends Person {
    @Relationship(type = "relation")
    private List<Son> sonList;
    @Relationship(type = "relation")
    private Wife wife;

    /**
     * 获取 sonList
     *
     * @return sonList
     */
    public List<Son> getSonList() {
        return sonList;
    }

    /**
     * 设置 sonList
     */
    public void setSonList(List<Son> sonList) {
        this.sonList = sonList;
    }

    /**
     * 获取 wife
     *
     * @return wife
     */
    public Wife getWife() {
        return wife;
    }

    /**
     * 设置 wife
     */
    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
