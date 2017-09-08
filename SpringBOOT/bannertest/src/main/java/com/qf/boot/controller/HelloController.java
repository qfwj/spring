/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: HelloController
 * @Description: ${DESCRIPTION}
 * @author: zhbo
 * @date: 2017/6/4 21:32
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
@RequestMapping("test")
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "Spring 你好";
    }

}
