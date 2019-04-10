package com.example.month.utils;


public class Http {

    private static final String BASE_URL="http://172.17.8.100/";//改域名在这里改

    public static final String HTTP_URL_REGISTER=BASE_URL+"small/user/v1/register";//注册

    public static final String HTTP_URL_LOGIN=BASE_URL+"small/user/v1/login";//登录

    public static final String HTTP_URL_BANNER=BASE_URL+"small/commodity/v1/bannerShow";//Banner接口

    public static final String HTTP_URL_HOME_SHOP_LIST=BASE_URL+"small/commodity/v1/commodityList";//商品列表

    public static final String HTTP_URL_SHOP_CAR=BASE_URL+"small/order/verify/v1/findShoppingCart";//获取购物车

    public static final String HTTP_URL_SHOP_DETAILS=BASE_URL+"small/commodity/v1/findCommodityDetailsById";//商品详情

    public static final String HTTP_URL_ADD_SHOP_CAR=BASE_URL+"small/order/verify/v1/syncShoppingCart";//添加购物车


}
