package com.qf.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.WebFilter;
import java.util.concurrent.Callable;


/**
 * Created by 擎风 on 2017/7/9.
 */
@Controller
@WebFilter
@RequestMapping("test")
public class TestController {
    @RequestMapping(value = "name", method = RequestMethod.GET)
    @ResponseBody
    public String test(String name){
        System.out.println(name);
        return name;
    }

    @RequestMapping(value = "age", method = RequestMethod.GET)
    @ResponseBody
    public WebAsyncTask test2(String age){
        System.out.println("age" + age);
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(3000); //假设是一些长时间任务
                ModelAndView mav = new ModelAndView("longtimetask");
                mav.addObject("result", "执行成功");
                System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
                return "啦啦啦";
            }
        };
        return new WebAsyncTask(callable);
    }

}
