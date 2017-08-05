/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.dubbox;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName: DubboConfig
 * @Description:
 * @author: zhbo
 * @date: 2017/8/5 11:56
 * @Copyright: 2017 . All rights reserved.
 */
@Configuration
@PropertySource("classpath:dubbo.properties")
@ImportResource({ "classpath:*.xml" })
public class DubboConfig {

}
