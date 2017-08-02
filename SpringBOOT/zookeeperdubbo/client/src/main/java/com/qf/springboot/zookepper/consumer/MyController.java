/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.springboot.zookepper.consumer;


import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MyController
 * @Description:
 * @author: zhbo
 * @date: 2017/7/30 12:10
 * @Copyright: 2017 . All rights reserved.
 */

@RestController
    public class MyController {
        @Reference
        private IService serviceImpl;

        @RequestMapping("/hello")
        public String home(){
            return serviceImpl.sayHello();
        }


}

