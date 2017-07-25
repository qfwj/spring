/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.zookeeper;

/**
 * @ClassName: ServerDto
 * @Description:
 * @author: zhbo
 * @date: 2017/7/15 14:52
 * @Copyright: 2017 . All rights reserved.
 */
public class ServerDTO {
    private Long id;
    private String name;
    private String address;

    /**
     * 获取 id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * 获取 address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置 address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}

