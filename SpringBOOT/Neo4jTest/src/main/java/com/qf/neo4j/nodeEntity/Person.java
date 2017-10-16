/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.neo4j.nodeEntity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * @ClassName: Person
 * @Description:
 * @author: zhbo
 * @date: 2017/10/16 15:40
 * @Copyright: 2017 . All rights reserved.
 */


@NodeEntity(label = "person")
public class Person {
    /**
     * 获取 name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置 age
     */
    public void setAge(int age) {
        this.age = age;
    }

    @GraphId
    Long id;

    String name;

    @Property(name="age")
    int age;
}
