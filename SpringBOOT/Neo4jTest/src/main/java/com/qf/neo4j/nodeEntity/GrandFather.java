/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.nodeEntity;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @ClassName: GrandFather
 * @Description:
 * @author: zhbo
 * @date: 2017/10/30 17:46
 * @Copyright: 2017 . All rights reserved.
 */
@NodeEntity(label = "grandfather")
public class GrandFather extends Person{
    @Relationship(type = "relation")
    private List<Father> fatherList;

    /**
     * 获取 fatherList
     *
     * @return fatherList
     */
    public List<Father> getFatherList() {
        return fatherList;
    }

    /**
     * 设置 fatherList
     */
    public void setFatherList(List<Father> fatherList) {
        this.fatherList = fatherList;
    }
}
