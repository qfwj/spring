/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.dubbox;

/**
 * @ClassName: TestServiceImpl
 * @Description:
 * @author: zhbo
 * @date: 2017/8/5 12:21
 * @Copyright: 2017 . All rights reserved.
 */

public class TestServiceImpl implements TestService {
    public String testHello(String name){
        System.out.print(name);
        return "成功了 少年！";
    }
}

