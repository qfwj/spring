/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.dubbox;

import com.alibaba.dubbo.config.annotation.Reference;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @author: zhbo
 * @date: 2017/8/5 12:17
 * @Copyright: 2017 . All rights reserved.
 */
@RestController
public class TestController {

    @Reference
    TestService exampleServiceImpl;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        String re = exampleServiceImpl.testHello("hhaha");
        return re;
    }
}

