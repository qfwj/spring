/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.zookeeper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: Test
 * @Description:
 * @author: zhbo
 * @date: 2017/7/20 22:22
 * @Copyright: 2017 . All rights reserved.
 */
@Configuration
public class Test {

    @Bean
    public ServerDTO getMM(@Value("re") String re){
        System.out.print(re);
        return new ServerDTO();
    }
}


