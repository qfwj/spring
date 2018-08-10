/**
 * Copyright(c) 2013-2018 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.test.testparambody;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: TestController
 * @Description:
 * @author: zhbo
 * @date: 2018/5/15 下午5:24
 * @Copyright: 2017 . All rights reserved.
 */

@Controller
public class TestController {

    @RequestMapping(value = "test_getparam", method = RequestMethod.GET)
    @ResponseBody
    public String testGetparam(TestVo vo ,@RequestParam String name, int age, String sex ){
        System.out.println("");
        return "get";
    }

    @RequestMapping(value = "test_getbody", method = RequestMethod.GET)
    public String testGetbody(@RequestBody String name, @RequestParam int age, String sex){
        System.out.println("");
        return "get";
    }

    @RequestMapping(value = "test_postparam", method = RequestMethod.POST)
    public String testpost(@RequestParam String name, @RequestParam int age, String sex){
        System.out.println("");

        return "get";
    }


    @RequestMapping(value = "test_postbody", method = RequestMethod.POST)
    @ResponseBody
    public String testPost(@RequestBody String name, @RequestParam int age, String sex, TestVo vo){
        System.out.println("");
        return "get";
    }


    @RequestMapping(value = "test_postVo", method = RequestMethod.POST)
    public String testPostVo(@RequestBody String name, @RequestParam int age, TestVo  vo){

        System.out.println("");
        return "get";
    }
}


class TestVo{
    String page;
    int num;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}