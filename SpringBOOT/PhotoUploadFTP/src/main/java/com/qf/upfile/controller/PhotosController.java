/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.upfile.controller;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @ClassName: PhotosController
 * @Description: ${DESCRIPTION}
 * @author: zhbo
 * @date: 2017/6/7 16:28
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
@RequestMapping("/photo")
public class PhotosController {

    @RequestMapping("/uploadFtp")
    @ResponseBody
    public String uploadPhotoFtp() throws IOException {
        File file = new File("C:\\Users\\puhui\\Downloads", "CentOS-7-x86_64-DVD-1611 (1).iso");
        FTPClient cfc = new FTPClient();
        cfc.setConnectTimeout(50000000);
        cfc.connect("192.168.1.102", 21);


        cfc.login("qingfeng","qaz!@#wsx$%^");
        cfc.enterLocalPassiveMode();

        cfc.setFileType(cfc.BINARY_FILE_TYPE); //设置下载文件为二进制模式
        cfc.setFileTransferMode(cfc.STREAM_TRANSFER_MODE); ///传输文件为流的形式


        /*cfc.changeWorkingDirectory("");*/
        cfc.getStatus();

        cfc.storeFile("CentOS-7-x86_64-DVD-1611.iso", new FileInputStream(file));

        cfc.logout();
        return "success";
}



    @RequestMapping("/uploadFtpSocket")
    @ResponseBody
    public String uploadPhotoFtpSocket(HttpServletRequest request, @RequestParam MultipartFile fileUpload) throws IOException {
        Socket socket =new Socket("196.168.1.104",21);
        OutputStream os = socket.getOutputStream();//字节输出流

        return "success";
    }

    @RequestMapping("/uploadSftp")
    @ResponseBody
    public String uploadPhotoSftp() throws IOException {
        ChannelSftp channel = null;
        try {
           /* FTPClient cfc = new FTPClient();
            cfc.connect("192.168.239.128", 21);
            cfc.login("ftpuser","123456");
            cfc.changeWorkingDirectory("/usr");
            cfc.storeFile("mytext.png", photoFiles[0].getInputStream());*/
            File file = new File("C:\\Users\\puhui\\Downloads", "Postman_v4.1.3.crx");

            String ftpHost = "172.168.1.102";
            ftpHost = "172.16.7.20";
            String port = "22";
            String ftpUserName = "intapp";
            String ftpPassword = "intappfinpu";

            JSch jsch = new JSch();
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            Session session =  jsch.getSession(ftpUserName, ftpHost, 22);
            session.setTimeout(500000);
            session.setPassword(ftpPassword);
            session.setConfig(config);

                session.connect(); // 通过Session建立链接
            channel = (ChannelSftp)session.openChannel("sftp"); // 打开SFTP通道
            channel.connect();
            /*channel.cd("/data/psreports/abs_attachments/");*/
            channel.cd("/");

            channel.put(new FileInputStream(file), "Postman_v4.1.3.crx");

            /*channel.put(fileUpload.getInputStream(),"my1212.png");*/
            return null;
        } catch (Exception e){
            e.printStackTrace();

            return null;
        } finally {
            if(channel != null)
                channel.disconnect();
            return null;
        }

    }

    @RequestMapping("/index")

    public String index(@RequestBody MultipartFile[] photoFiles){

        return "photo";
    }
}
