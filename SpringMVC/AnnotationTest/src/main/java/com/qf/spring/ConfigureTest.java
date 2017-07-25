/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ConfigureTest
 * @Description:
 * @author: zhbo
 * @date: 2017/7/22 9:25
 * @Copyright: 2017 . All rights reserved.
 */
@Configuration

public class ConfigureTest {
    @Bean
    public Mytest getMytest(){
        Mytest my = new Mytest();
        return my;
    }
}

