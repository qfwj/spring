/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */

import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

/**
 * @ClassName: TestMain
 * @Description: ${DESCRIPTION}
 * @author: zhbo
 * @date: 2017/6/18 21:42
 * @Copyright: 2017 . All rights reserved.
 */
public class TestMain {

    public static  int  BEGIN_COUNT = 0;
    public void sqlTest() throws Exception{
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/agile-portal";
        String username = "root";
        String password = "hanlei124";
        Connection conn = null;
        Class.forName(driver); //classLoader,加载对应驱动
        conn = (Connection) DriverManager.getConnection(url, username, password);
        String sql = "     INSERT INTO ap_sla_info (team_name, data_time, data_value, is_actual)  " +
                " VALUES ('CIF-实时', '20170eew44', 66.0, true);" +
                "INSERT INTO ap_sla_info (team_name, data_time, data_value, is_actual)  " +
                " VALUES ('CIF-实时', '20170w44', 66.0, true);";
        PreparedStatement pstmt;
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
   /* private  File[] getPortraitFiles(String[] files, String[] nameStrArr){
        Decoder decoder =  Base64.getDecoder();
        try {
            int i = 0;
            File[] fileArr = new File[files.size()];
            for(Object temp : files) {
                Object fileStr = ((JSONObject)temp).get("PHOTO");
                if(fileStr == null)
                    continue;
                fileArr[i]  = new File(nameStrArr[i]);
                ++ i;
                InputStream inputTemp = new FileInputStream(fileArr[i]);
                inputTemp.read(decoder.decode(fileStr.toString()));
                inputTemp.close();
            }
            return fileArr;
        } catch (Exception e){
            e.printStackTrace();

            return null;
        }
    }*/
    public static void main(String args[]) throws Exception{
        File ss = new File("D:text.txt");
        ss.createNewFile();

        OutputStream out = new FileOutputStream(ss.getAbsolutePath());
        out.write("haha".getBytes());
        out.flush();
        out.close();

        String[] strArr = new String[1];
        strArr[0] = "23213";

        Thread testThr = new Thread(){
            @Override
            public void run(){
                int i = BEGIN_COUNT;
                for(; i < 100;++ i){
                    System.out.println(i);
                }

            }
        };
        testThr.start();
        Thread.sleep(1);
        /*while(true){

            System.out.println("哈哈哈");
        }*/

        System.out.println("哈哈哈");

       /* File fileTemp = new File("C://Users//puhui//Desktop//微信截图_20170627145831.png");
        InputStream ss = new FileInputStream(fileTemp);

        Base64.Encoder sen = Base64.getEncoder();
        *//*String sshStr = new String(sen.encode(byteTest));*//*


        *//*byte[] outByte = deBase.decode(sshArr);*//*

        *//*byte[] byteTest = new byte[ss.available()];
        ss.read(byteTest);
         byte[] encodeArr = sen.encode(byteTest);*//*

       byte[] byteTest1 = new byte[1024];
       List<Byte> list = new ArrayList<Byte>();
        ByteArrayOutputStream outt = new ByteArrayOutputStream();
        while((ss.read(byteTest1)) != -1){
            outt.write(byteTest1);
        }

        outt.flush();

        Decoder deBase = Base64.getDecoder();
        byte[] outByte = deBase.decode( sen.encode(outt.toByteArray()));
        String imgFilePath = "C://Users//puhui//Desktop//222.jpg";//新生成的图片
        OutputStream out = new FileOutputStream("99.jpg");
        out.write(outByte);
        out.flush();
        out.close();

*/
/*
        BASE64Encoder encoder = new BASE64Encoder();
        String ssh = encoder.encode(byteTest);//返回Base64编码过的字节数组字符串
        ss.close();*/



      /*  BASE64Decoder decoder = new BASE64Decoder();
            //Base64解码
            byte[] b = decoder.decodeBuffer(ssh);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "C://Users//puhui//Desktop//222.jpg";//新生成的图片
            OutputStream out = new FileOutputStream("222.jpg");
            out.write(b);
            out.flush();
            out.close();*/

       /* while(ss.read(byteTest) != -1){

        }*/
    }
}
