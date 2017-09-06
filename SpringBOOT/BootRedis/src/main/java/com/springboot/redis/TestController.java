/**
 * Copyright(c) 2013-2017 by ZhBo Inc.
 * All Rights Reserved
 */
package com.springboot.redis;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: TestController
 * @Description:
 * @author: zhbo
 * @date: 2017/9/5 22:53
 * @Copyright: 2017 . All rights reserved.
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public  String test(String test, HttpServletRequest request, HttpSession session){
        //HttpHeaders headers = request.getHeaders();
        session.setAttribute("user", "helloword");
        return "test";
    }
}

