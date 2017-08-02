/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.springboot.zookepper;


import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @ClassName: ServiceImpl
 * @Description:
 * @author: zhbo
 * @date: 2017/7/30 12:02
 * @Copyright: 2017 . All rights reserved.
 */

@Service
@EnableAutoConfiguration
public class ServiceImpl implements IService {
        @Override
        public String sayHello() {
            return "hello dubbo";
        }

}

