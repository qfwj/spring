/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.httptest.controller;



import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.ContentEncodingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @ClassName: HttpClient
 * @Description: ${DESCRIPTION}
 * @author: zhbo
 * @date: 2017/6/17 14:13
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
@RequestMapping("/client")
public class HttpClientTest {

    @RequestMapping("/get")
    public  String  getMessage(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        PoolingHttpClientConnectionManager conPoolMa = new PoolingHttpClientConnectionManager();
        conPoolMa.setDefaultMaxPerRoute(5);
        HttpHost localhost = new HttpHost("localhost", 8080);
        HttpRoute sru = new HttpRoute(localhost);
        conPoolMa.setMaxPerRoute(sru, 3);

        CloseableHttpClient client= HttpClients.custom().setConnectionManager(conPoolMa).build();
        HttpGet sd = new HttpGet("http://localhost:8080/mytest1.txt");
        HttpPost sp = new HttpPost("");
         /*httpPost.setEntity(reqEntity);   传输文件
*/
        CloseableHttpResponse csrspon =  client.execute(sd);
        InputStream in = csrspon.getEntity().getContent();
        in.close();//保证连接池可以复用
        csrspon.close();// 导致连接关闭，不可复用
        FileOutputStream sss = null;



    }
}
