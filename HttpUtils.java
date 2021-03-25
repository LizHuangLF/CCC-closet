package com.exam.closet_f.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static int BUFFER_SIZE = 4096;
    private static final String TAG = "HttpUtils";
    private static final int TIME_OUT = 5*1000;

    /*
     *   Get：
     *   基于http的网络通信中，移动端的任务是：
     *   (1) 构造http请求报文(请求行，请求头，请求体)
     *       get请求，将请求参数附加到url
     *   (2) 发送http请求，将封装好的http请求报文发送到服务器
     *   (3) 接收服务器响应结果，通过输入流
     *
     * */

    public static String doGet(String strUrl, String param) throws Exception {

        //Log.i("doGet-",strUrl);
        //Log.i("doGet-",param);

        String result = "";
        HttpURLConnection connection = null;

        // 1.1 构造URL字符串，创建URL对象
        if (param != null)
            strUrl += "?" + param;
        URL url = new URL(strUrl);
        // 1.2 得到HttpURLConnection对象，处理http请求
        connection = (HttpURLConnection)url.openConnection();

        // 2.1 设置请求头信息
        connection.setReadTimeout(TIME_OUT); //超时
        // 2.2 根据上述配置生成请求头部信息
        connection.connect();

        //3. 连接成功
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // 3.1 通过输入流接受服务器响应结果
            InputStream is = connection.getInputStream();
            // 3.2 将相应结果转换为需要的数据格式（本例中，服务器响应结果是一个字符串）
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            // 3.3 关闭输入流
            reader.close(); // 关闭缓冲流，后打开先关闭
            is.close(); // 关闭输入流
        }
        // 4. 断开连接
        connection.disconnect();

        Log.i("---HttpUtils","doGet");
        return result;
    }

    /*
     *   Post：
     *   基于http的网络通信中，移动端的任务是：
     *   (1) 构造http请求报文(请求行，请求头，请求体)
     *       post请求，利用OutputStream将请求参数写入请求体
     *   (2) 发送http请求，将封装好的http请求报文发送到服务器
     *   (3) 接收服务器响应结果，通过InputStream
     *
     * */

    public static String doPost(String strUrl, String param) throws Exception {

        String result = "";
        HttpURLConnection connection = null;

        // 1.1 构造URL字符串，创建URL对象
        URL url = new URL(strUrl);
        // 1.2 得到HttpURLConnection对象，处理http请求
        connection = (HttpURLConnection)url.openConnection();

        // 2.1 设置请求头信息
        // 设置是否允许输出，post请求参数放在http正文内，必须设为true，默认false
        connection.setDoOutput(true);
        // 设置是否从httpUrlConnection读入，默认true
        connection.setDoInput(true);
        // Post请求不能使用缓存
        connection.setUseCaches(false);
        // 设置请求超时时间
        connection.setReadTimeout(TIME_OUT);
        // 设置请求方式为POST，默认是GET
        connection.setRequestMethod("POST");

        // 2.2 根据上述配置生成请求头部信息
        // 所有的头部配置都必须在connect（）之前完成，之后的配置无效
        connection.connect();

        // 2.3 对于post请求，利用OuyputStream将请求参数写入http正文
        if (param != null) {
            OutputStream os = connection.getOutputStream();
            PrintWriter out = new PrintWriter(os); //字节流→高级流
            out.print(param); // 向输出流对象写入数据
            out.flush(); // 刷新对象输出流
            // 执行关闭流语句时，会根据输出流中的内容生成http正文
            // 关闭输出流之后，就不能再向输出流写入任何数据
            out.close();
        }

        //3. 连接成功,则发送http请求，读取响应结果
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // 3.1 通过输入流接受服务器响应结果
            InputStream is = connection.getInputStream();
            // 3.2 将相应结果转换为需要的数据格式（本例中，服务器响应结果是一个字符串）
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            // 3.3 关闭输入流
            reader.close(); // 关闭缓冲流，后打开先关闭
            is.close(); // 关闭输入流
        }

        // 4. 断开连接
        connection.disconnect();

        Log.i("---HttpUtils","doPost");
        return result;
    }
}
