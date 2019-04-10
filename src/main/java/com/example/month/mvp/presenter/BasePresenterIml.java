package com.example.month.mvp.presenter;


import com.example.month.mvp.model.BaseModel;
import com.example.month.mvp.model.BaseModelIml;
import com.example.month.mvp.view.BaseView;

import java.util.Map;

import okhttp3.FormBody;
/**
 * author:AbnerMing
 * date:2019/3/21
 */
public class BasePresenterIml implements BasePresenter, BaseModel.CallBackListener {

    private BaseView baseView;
    private BaseModel baseModel;

    public BasePresenterIml(BaseModel baseModel, BaseView baseView) {
        this.baseModel = baseModel;
        this.baseView = baseView;
    }
    //传递头参数
    private Map<String,String> headMap;
    public void setHead(Map<String,String> headMap){
        this.headMap=headMap;
    }
    @Override
    public void doGetHttp(int type, String url) {
        ((BaseModelIml)baseModel).setHead(headMap);//添加头参数
        baseModel.doGetHttp(type, url, this);
    }

    @Override
    public void doPostHtpp(int type, String url, FormBody.Builder builder) {
        ((BaseModelIml)baseModel).setHead(headMap);//添加头参数
        baseModel.doPostHtpp(type, url, builder, this);
    }

    @Override
    public void success(int type, String data) {
        baseView.success(type, data);
    }

    @Override
    public void fail(int type, String error) {
        baseView.fail(type, error);
    }

    public void destory() {
        if (baseModel != null) {
            baseModel = null;
        }
        if (baseView != null) {
            baseView = null;
        }
        System.gc();

    }
}
