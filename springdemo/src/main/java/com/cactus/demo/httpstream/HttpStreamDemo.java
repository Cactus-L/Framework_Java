package com.cactus.demo.httpstream;

/**
 * Created by liruigao
 * Date: 2019-11-01 19:52
 * Description:
 */
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class HttpStreamDemo {

    private static  final String  url="https://www.baidu.com";
    public  static   String processorStream(String url, String param){
        System.out.println("========");
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
//             conn.setRequestProperty("user-agent",
//                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            int i = 0;
            while ((line = in.readLine()) != null) {
//                result += line;
                System.out.println((i++) + " - " + line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;

    }

    public static void main(String[] args) {

        HttpStreamDemo.processorStream(url,"");
    }
}
