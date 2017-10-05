/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.httpproxy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: TestController
 * @Description:
 * @author: zhbo
 * @date: 2017/9/25 14:45
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
@RequestMapping("test")
public class TestController {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String testRe(String hshhs) {
        return "成功" + " yes";
    }
}
