package com.example.month.activity;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.month.R;
import com.example.month.base.BaseActivity;
import com.example.month.bean.BaseBean;
import com.example.month.bean.ShopCarBean;
import com.example.month.bean.ShopCarDetailsBean;
import com.example.month.bean.ShopDetailsPicTrueBean;
import com.example.month.net.OkHttpUtils;
import com.example.month.utils.Http;
import com.example.month.utils.SharedUtils;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;

//商品详情页
public class ShopCarDescActivity extends BaseActivity {


    private XBanner mXBanner;
    private TextView mTitle, mPrice;
    private String shopid;

    @Override
    protected void initView() {
        mXBanner = (XBanner) get(R.id.xbanner);
        mTitle = (TextView) get(R.id.tv_shop_title);
        mPrice = (TextView) get(R.id.tv_shop_price);

    }

    @Override
    protected void initData() {
        Map<String, String> headMap = new HashMap<>();
        headMap.put("userId", SharedUtils.getInt(this, "userid") + "");
        headMap.put("sessionId", SharedUtils.getString(this, "sessionid"));
        shopid = getIntent().getStringExtra("shopid");
        getPresnter().setHead(headMap);
        getPresnter().doGetHttp(0, Http.HTTP_URL_SHOP_DETAILS + "?commodityId=" + shopid);

        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(ShopCarDescActivity.this).load(((ShopDetailsPicTrueBean) model)
                        .getImageurl()).into((ImageView) view);
            }
        });

        setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShopCar();
            }
        }, R.id.add_shop_car);

        doShopCar();

    }

    private JSONArray jsonArray = new JSONArray();

    //先获取用户的购物车数据
    private void doShopCar() {
        Map<String, String> headMap = new HashMap<>();
        headMap.put("userId", SharedUtils.getInt(this, "userid") + "");
        headMap.put("sessionId", SharedUtils.getString(this, "sessionid"));
        new OkHttpUtils().setHead(headMap).get(Http.HTTP_URL_SHOP_CAR).result(new OkHttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                //  Log.i("ShopCarFragment", data);
                ShopCarBean bean = new Gson().fromJson(data, ShopCarBean.class);
                try {
                    for (int i = 0; i < bean.getResult().size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("commodityId", bean.getResult().get(i).getCommodityId() + "");
                        jsonObject.put("count", bean.getResult().get(i).getCount() + "");
                        jsonArray.put(jsonObject);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void fail(String error) {

            }
        });
    }

    //添加购物车
    private void addShopCar() {
        Map<String, String> headMap = new HashMap<>();
        headMap.put("userId", SharedUtils.getInt(this, "userid") + "");
        headMap.put("sessionId", SharedUtils.getString(this, "sessionid"));

        JSONObject jsonObject = new JSONObject();
        try {
            boolean isJson = true;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.get("commodityId").equals(shopid)) {
                    isJson = false;
                    jsonObject1.put("commodityId", shopid);
                    jsonObject1.put("count", Integer.parseInt(jsonObject1.getString("count"))+1);
                }
            }
            if (isJson) {
                jsonObject.put("commodityId", shopid);
                jsonObject.put("count", "1");
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        FormBody.Builder builder = new FormBody.Builder();

        Log.i("ShopCarFragment", jsonArray.toString());
        builder.add("data", jsonArray.toString());
        new OkHttpUtils().setHead(headMap).put(Http.HTTP_URL_ADD_SHOP_CAR, builder).result(new OkHttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                Log.i("ShopCarDescActivity", data);
                BaseBean baseBean = new Gson().fromJson(data, BaseBean.class);
                toast(baseBean.getMessage());
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 0) {
            //Log.i("ShopCarDescActivity", data);
            ShopCarDetailsBean shopCarDetailsBean = new Gson().fromJson(data, ShopCarDetailsBean.class);
            String details = shopCarDetailsBean.getResult().getDetails();

            mTitle.setText(shopCarDetailsBean.getResult().getCommodityName());
            mPrice.setText("" + shopCarDetailsBean.getResult().getPrice());

            String[] imageUrl = shopCarDetailsBean.getResult().getPicture().split(",");

            List<ShopDetailsPicTrueBean> list = new ArrayList<>();
            for (int a = 0; a < imageUrl.length; a++) {
                ShopDetailsPicTrueBean shopDetailsPicTrueBean = new ShopDetailsPicTrueBean();
                shopDetailsPicTrueBean.setImageurl(imageUrl[a]);
                list.add(shopDetailsPicTrueBean);
            }
            mXBanner.setBannerData(list);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_car_desc;
    }

}
