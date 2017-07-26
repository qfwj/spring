/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.qf.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: Contoller
 * @Description:
 * @author: zhbo
 * @date: 2017/7/22 9:22
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
@RequestMapping(value="/test")
public class Contoller {
    @RequestMapping(value="/hello")
    public  String test(){
        Watcher watcher =
        return "hello world";
    }
}

