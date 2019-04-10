package com.example.month.mvp.model;


import com.example.month.net.OkHttpUtils;

import java.util.Map;

import okhttp3.FormBody;

public class BaseModelIml implements BaseModel {
    //get请求
    @Override
    public void doGetHttp(int type,String url, final CallBackListener listener) {
        //执行网络请求
        doHttp(type,url, null, listener);
    }

    //post请求
    @Override
    public void doPostHtpp(int type,String url, FormBody.Builder builder, final CallBackListener listener) {
        doHttp(type,url, builder, listener);
    }

    //传递头参数
    private Map<String,String> headMap;
    public void setHead(Map<String,String> headMap){
        this.headMap=headMap;
    }

    //网络请求
    private void doHttp(final int type, String url, FormBody.Builder builder, final CallBackListener listener) {
        OkHttpUtils okhttp = new OkHttpUtils();

        if(headMap!=null){
            okhttp.setHead(headMap);
        }
        if (builder == null) {
            okhttp.get(url);
        } else {
            okhttp.post(url, builder);
        }
        okhttp.result(new OkHttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                listener.success(type,data);
            }

            @Override
            public void fail(String error) {
                listener.fail(type,error);
            }
        });
    }
}
