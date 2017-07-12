package com.qf.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 擎风 on 2017/7/11.
 */

@Configuration
public class MyInterceptor implements AsyncHandlerInterceptor{
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest var1, HttpServletResponse var2, Object var3) throws Exception{
        System.out.print("我是哪个处理阶段");
    }

    @Override
   public  boolean preHandle(HttpServletRequest var1, HttpServletResponse var2, Object var3) throws Exception{
        System.out.println("预处理阶段");
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest var1, HttpServletResponse var2, Object var3, ModelAndView var4) throws Exception{
        System.out.println("后处理阶段");
    }
    @Override
    public void afterCompletion(HttpServletRequest var1, HttpServletResponse var2, Object var3, Exception var4) throws Exception{
        System.out.println("完成处理阶段");
    }

}
