/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.http.controller;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName: HttpTestController
 * @Description: 测试HTTP断点重传机制
 * @author: zhbo
 * @date: 2017/6/13 10:12
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
@RequestMapping("/httptest")
public class HttpTestController {

    @RequestMapping("/upload")
    public  String httpReqUpload() throws  Exception{
        HttpClient client = new DefaultHttpClient();

        /*HttpHead httpHead = new HttpHead("http://localhost:8080/mytest1.txt");*/
        HttpGet httpget = new HttpGet("http://localhost:8080/ewewew.iso");

        HttpResponse response;
        FileOutputStream fo = null;
        try {


            response = client.execute(httpget);//此时已经可以获取文件总长度；因此可以关闭此次连接；
            // 根据总长进行多线程的分段请求
            /*关闭连接
            client = new DefaultHttpClient();
            httpget.setHeader("range","bytes=200000000-200001000");
            response = client.execute(httpget);*/

            for(10){
                new Thread(){
                    run(){
                        client = new DefaultHttpClient();
                        httpget.setHeader("range","bytes=200000000-200001000");
                        response = client.execute(httpget);*/
                        HttpEntity sd = response.getEntity();
                        File  sffr  = new File("wewe.txt");//写进同一个文件
                        fo = new FileOutputStream(sffr);
                        sd.writeTo(fo); //开启i/o流
                    }
                };
            }


            HttpEntity sd = response.getEntity();
            File  sffr  = new File("wewe.txt");
            fo = new FileOutputStream(sffr);
            sd.writeTo(fo); //开启i/o流
            Header[] ss = response.getAllHeaders();
        } catch ( Exception e){
            e.printStackTrace();
            fo.close();
        }
        fo.close();
        return null;
    }
}
