package com.example.month.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpUtils {

    private int HTTP_SUCCESS = 1000;//成功

    private int HTTP_FAIL = 1001;//失败
    private HttpListener mHttpListener;

    //get请求
    public OkHttpUtils get(String url) {
        doHttp(url, 0, null);
        return this;
    }

    //put添加数据
    public OkHttpUtils put(String url, FormBody.Builder bodyBuilder) {
        doHttp(url, 2, bodyBuilder);
        return this;
    }

    //post请求
    public OkHttpUtils post(String url, FormBody.Builder bodyBuilder) {
        doHttp(url, 1, bodyBuilder);
        return this;
    }

    //设置头部信息
    private Map<String, String> headMap;

    public OkHttpUtils setHead(Map<String, String> headMap) {
        this.headMap = headMap;
        return this;
    }

    //网络请求
    private void doHttp(String url, int type, FormBody.Builder bodyBuilder) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.i("intercept", request.url().host());
                return chain.proceed(request);
            }
        }).build();

        Request.Builder builder = new Request.Builder();


        //添加头部信息
        if (headMap != null) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
//        builder.addHeader("userid", SharedUtils.getString());
//        builder.addHeader("sessionid", SharedUtils.getString());

        if (type == 0) {
            builder.get();
        } else if (type == 1) {
            builder.post(bodyBuilder.build());
        } else {
            builder.put(bodyBuilder.build());//put添加
        }
        builder.url(url);
        Request request = builder.build();
        final Message message = Message.obtain();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                message.what = HTTP_FAIL;
                message.obj = e.getMessage();//错误的信息
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                message.obj = response.body().string();
                message.what = HTTP_SUCCESS;
                handler.sendMessage(message);
            }
        });
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HTTP_SUCCESS) {
                String data = (String) msg.obj;
                mHttpListener.success(data);
            } else {
                String error = (String) msg.obj;
                mHttpListener.fail(error);
            }
        }
    };

    //传递接口
    public void result(HttpListener mHttpListener) {
        this.mHttpListener = mHttpListener;
    }

    public interface HttpListener {
        void success(String data);

        void fail(String error);
    }

}
