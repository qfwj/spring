/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.httpproxy.test1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: HttpBlindProxy
 * @Description:
 * @author: zhbo
 * @date: 2017/9/25 14:40
 * @Copyright: 2017 . All rights reserved.
 */
public class HttpBlindProxy {
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        ServerSocket server = new ServerSocket(8088);
        while (true) {
//对应Https连<span style="color:#ff0000;">接建立过程第一步</span>,接收浏览器向代理建立的Socket连接
            Socket client = server.accept();
            new Worker(client).start();
        }
    }
}

//真正的逻辑处理
class Worker extends Thread {
    //http协议内容中的CRLF
    private static final String CRLF = "\r\n";
    private final Socket client;

    Worker(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        //Web Server地址和端口号.
        String serverAddr = null;
        // 查看是否存在端口号,如果不存在使用默认端口号80
        int serverPort = 8080;
        try {
            // java1.7特性之TWR
            try (InputStream clientIs = client.getInputStream();
                 OutputStream clientOs = client.getOutputStream();) {
                //默认只读取前100K,假定所有的头部都不超过100K就可以解析到Host头部.这块也是不好的地方
                byte[] data = new byte[102400];
                int length = -1;
                length = clientIs.read(data);
                String input = new String(data, 0, length);
                // 获取请求行,根据HTTP协议规范,第一行为请求行.
                String reqLine = input.substring(0, input.indexOf(CRLF));
                if (reqLine == null) {
                    client.close();
                }
                System.out.println(reqLine);
                // 开始解析Web Server URL 和 端口号
                // 根据HTTP协议规范,请求行中包含: Method Request-URL
                // HTTP-Version,字段间以空格符隔开,并且末尾添加换行
                String[] params = reqLine.split(" ");
                String reqMethod = params[0];
                String reqUrl = params[1];
                String serverUrl = null;
                //解析远端主机.
                if (reqUrl.startsWith("/")) {
                    int location = 0;
                    while ((location = input.indexOf(CRLF)) > 0) {
                        String header = input.substring(0, location);
                        // 根据HTTP协议规范,如果请求行中使用相对路径,则Web Server主机由Host头部决定.
                        // 需要注意HTTP协议规范中是大小写不敏感的,所以需要使用equalsIgnoreCase
                        if (header.length() > 5 && header.substring(0, 5).equalsIgnoreCase("Host:")) {
                            String host = header.substring("Host:".length(), header.length());
                            serverUrl = host;
                            break;
                        }
                        input = input.substring(location + CRLF.length());
                    }
                } else {
                    // 根据HTTP协议规范,如果请求行中使用绝对路径,则Web
                    // Server主机是URL的一部分,且必须忽略Host头部.
                    serverUrl = reqUrl;
                }
                // url: http://www.baidu.com/index.html
                if (serverUrl.indexOf("//") > 0) {
                    serverUrl = serverUrl.substring(serverUrl.indexOf("//") + 2);
                }
                // 去除URL中的路径只保留域名: www.baidu.com/index.html
                if (serverUrl.indexOf("/") > 0) {
                    serverUrl = serverUrl.substring(0, serverUrl.indexOf("/"));
                }
                // 开始获取Web Server地址和端口号.
                serverAddr = serverUrl;
                if (serverUrl.indexOf(":") > 0) {
                    serverAddr = serverUrl.substring(0, serverUrl.indexOf(":"));
                   //serverPort = Integer.parseInt(serverUrl.substring(serverUrl.indexOf(":") + 1));
                }
                // 接下来开始真正的代理转发逻辑.
                try (Socket serverClient = new Socket("127.0.0.1", serverPort);
                     InputStream serverIs = serverClient.getInputStream();
                     OutputStream serverOs = serverClient.getOutputStream()) {
                    System.out.println("local port is: " + serverClient.getLocalPort());
                    //判断是否是CONNECT方法
                    if (reqMethod.equalsIgnoreCase("CONNECT")) {
                        String proxyResponse = "HTTP/1.1 200 Connection Established" + CRLF +
                                "Proxy-agent: Anyone-BlindProxyServer/1.0" + CRLF +
                                CRLF;
                        clientOs.write(proxyResponse.getBytes());
                        clientOs.flush();
                    } else {
                        //其他的HTTP方法,将读取到的数据原封不动的发送到Server端.
                        serverOs.write(data, 0, length);
                        serverOs.flush();
                    }
                    while (!client.isInputShutdown() || !serverClient.isInputShutdown()) {
                        // 将客户端的输入流管道输入到server sock的输出流.
                        //client to server.
                        new PipeThread(client, serverClient, clientIs, serverOs).start();
                        //server to clien.
                        new PipeThread(serverClient, client, serverIs, clientOs).start();
                        try {
                            java.lang.Thread.sleep(2000);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }
                }
            }
        } catch (Exception e) {
            try {
                client.close();
            } catch (Exception e2) {
            }
            System.out.println("failed to connect to server[" + serverAddr + "] : [" + serverPort + "]...");
        }
    }
}

class PipeThread extends Thread {
    private final Socket client;
    private final Socket server;
    private final InputStream is;
    private final OutputStream os;

    public PipeThread(Socket client, Socket server, InputStream is, OutputStream os) {
        this.client = client;
        this.server = server;
        this.is = is;
        this.os = os;
    }

    @Override
    public void run() {
        try {
            byte[] data = new byte[102400];
            int length = -1;
            while (!client.isInputShutdown()) {
                if ((length = is.read(data)) != -1) {
                    os.write(data, 0, length);
                    os.flush();
                } else {
                    //client端已经关闭了输出流.
                    server.shutdownOutput();
                    client.shutdownInput();
                }
            }
        } catch (Exception e) {
        }
    }
}

