package com.exam.closet_f.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtils {
    private static String result;//响应结果


    public static String doGetSync(String strUrl, HashMap<String, String> params) throws Exception {
        result=null;
        //处理参数
        if (params!=null){
            int pos=0;
            StringBuilder tmpParams=new StringBuilder();
            for (String key:params.keySet()){
                if (pos>0){
                    tmpParams.append("&");
                }
                tmpParams.append(String.format("%s=%s",key,params.get(key)));
                pos++;
            }
            strUrl+="?"+tmpParams.toString();
        }

        //创建OkHttpClient客户端
        OkHttpClient client=new OkHttpClient.Builder()  //Builder是辅助类，使用建造者模式
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        //创建一个Request对象，描述了OkHttp将要发送的请求
        Request request=new Request.Builder()       //Builder是辅助类
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0")
                .get()                              //默认Get请求，可以不写
                .url(strUrl)
                .build();
        //创建一个Call对象，用于执行请求并获取响应
        Call call=client.newCall(request);
        //获取Response对象
        Response response=call.execute();

        if (response.isSuccessful()){
            //获取响应结果
            result=response.body().string();
        }else {
            throw new IOException("Unexpected code:"+response.code()+"---"+response.message());
        }

        return result;
    }

    public static String doPostSync(String strUrl, HashMap<String, String> params) throws IOException {
        result=null;
        FormBody.Builder builder=new FormBody.Builder();
        for (String key:params.keySet()){
            //追加表单信息
            builder.add(key,params.get(key));
        }
        RequestBody formBody=builder.build();
        Request request=new Request.Builder()       //Builder是辅助类
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0")
                .post(formBody)
                .url(strUrl)
                .build();
        //创建OKHttpClient客户端
        OkHttpClient client=new OkHttpClient();

        Response response=client.newCall(request).execute();
        if (response.isSuccessful()){
            //获取响应结果
            result=response.body().string();
        }else {
            throw new IOException("Unexpected code:"+response.code()+"---"+response.message());
        }
        return result;
    }

    public static void doGetSync(String strUrl, HashMap<String, String> params, final Handler myHandler) throws Exception {
        result=null;
        //处理参数
        if (params!=null){
            int pos=0;
            StringBuilder tmpParams=new StringBuilder();
            for (String key:params.keySet()){
                if (pos>0){
                    tmpParams.append("&");
                }
                tmpParams.append(String.format("%s=%s",key,params.get(key)));
                pos++;
            }
            strUrl+="?"+tmpParams.toString();
        }

        //创建OkHttpClient客户端
        OkHttpClient client=new OkHttpClient.Builder()  //Builder是辅助类，使用建造者模式
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        //创建一个Request对象，描述了OkHttp将要发送的请求
        final Request request=new Request.Builder()       //Builder是辅助类
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0")
                .get()                              //默认Get请求，可以不写
                .url(strUrl)
                .build();
        //创建一个Call对象，用于执行请求并获取响应
        Call call=client.newCall(request);
        //获取Response对象,在子线程中完成
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    //获取响应结果
                    ResponseBody body=response.body();
                    //获取响应结果
                    if (body!=null){
                        result=body.string();
                        //result=new String(body.string().getBytes("iso-8859-1"),"UTF-8");
                    }
                }else {
                    throw new IOException("Unexpected code:"+response.code()+"---"+response.message());
                }
                Message msg=myHandler.obtainMessage();
                msg.obj=result;
                Log.i("Test:",result);
                myHandler.sendMessage(msg);
            }
        });

    }

    public static void doPostSync(String strUrl, HashMap<String,String> params, final Handler myHandler) throws IOException {
        result="";
        FormBody.Builder builder=new FormBody.Builder();
        for (String key:params.keySet()){
            //追加表单信息
            builder.add(key,params.get(key));
        }
//        Log.i("1111111111","oooooooooo");
        // 生成表单对象
        RequestBody formBody=builder.build();
        Request request=new Request.Builder()       //Builder是辅助类
                .addHeader("User-Agent","OkHttp Header.java")
                .post(formBody)
                .url(strUrl)
                .build();
        //创建OKHttpClient客户端
        OkHttpClient client=new OkHttpClient();

//        Log.i("1111111111","222222222");

        //创建一个Call对象，用于执行请求并获取响应
        Call call=client.newCall(request);
        //获取Response对象,在子线程中完成
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    ResponseBody body=response.body();
                    //获取响应结果
                    if (body!=null){
                        result=body.string();
//                        Log.i("1111111111","444444444444");
                    }

                }else {
                    if (response.isRedirect()){
                        result=response.body().string();
                    }else
                        throw new IOException("Unexpected code:"+response.code()+"---"+response.message());
                    //200 - 请求成功
                    //301 - 资源（网页等）被永久转移到其它URL
                    //404 - 请求的资源（网页等）不存在
                    //500 - 内部服务器错误
                }
                Message msg=myHandler.obtainMessage();
                msg.obj=result;
                myHandler.sendMessage(msg);
//                Log.i("1111111111","33333333333");
            }
        });

    }
}
