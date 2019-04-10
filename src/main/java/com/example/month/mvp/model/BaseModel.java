package com.example.month.mvp.model;

import okhttp3.FormBody;

public interface BaseModel {

    interface CallBackListener {
        void success(int type,String data);

        void fail(int type,String error);
    }

    //公共的get请求
    void doGetHttp(int type,String url, CallBackListener listener);

    //公共的post请求
    void doPostHtpp(int type,String url, FormBody.Builder builder, CallBackListener listener);
}
