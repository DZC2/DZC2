package com.example.month.mvp.presenter;


import okhttp3.FormBody;


public interface BasePresenter {
    //公共的get请求
    void doGetHttp(int type,String url);

    //公共的post请求
    void doPostHtpp(int type,String url, FormBody.Builder builder);
}
