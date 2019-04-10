package com.example.month.mvp.view;


public interface BaseView {

    //成功的方法 type 类型， 判断你是执行哪个网络请求  data是服务端返回的数据
    void success(int type, String data);

    //失败的方法
    void fail(int type, String error);
}
