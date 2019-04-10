package com.example.month.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.month.R;
import com.example.month.adapter.ShopCarAdapter;
import com.example.month.base.BaseFragment;
import com.example.month.bean.ShopCarBean;
import com.example.month.net.OkHttpUtils;
import com.example.month.utils.Http;
import com.example.month.utils.SharedUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCarFragment extends BaseFragment {
    private RecyclerView mRecycler;
    private ShopCarAdapter mShopCarAdapter;
    private CheckBox mChecketBox;
    private List<ShopCarBean.ResultBean> resultShopCar = new ArrayList<>();

    private int allPrice = 0;
    private TextView mPrice;

    @Override
    protected void initView(View viewChild) {
        mRecycler = (RecyclerView) get(R.id.recycler);
        mChecketBox = (CheckBox) get(R.id.checkbox);
        mPrice = (TextView) get(R.id.tv_price);
    }

    @Override
    protected void initData() {
        mShopCarAdapter = new ShopCarAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(linearLayoutManager);
        mRecycler.setAdapter(mShopCarAdapter);

        mChecketBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked= mChecketBox.isChecked();
                for (int i = 0; i < resultShopCar.size(); i++) {
                    resultShopCar.get(i).setChecked(isChecked);//isChecked true为选中  false为反选
                    if (resultShopCar.get(i).isChecked()) {
                        int price = resultShopCar.get(i).getPrice();
                        int count = resultShopCar.get(i).getCount();
                        allPrice = allPrice + price * count;
                    }
                }

                if (!isChecked) {
                    allPrice = 0;
                }
                //刷新适配器
                mShopCarAdapter.setList(resultShopCar);
                String html = "合计：" + "<font color='#d43c3c'>" + allPrice + "</font>";
                mPrice.setText(Html.fromHtml(html));
            }
        });


        mShopCarAdapter.setOnCallBackDataListener(new ShopCarAdapter.OnCallBackDataListener() {
            @Override
            public void changeData(List<ShopCarBean.ResultBean> list) {

                int num = 0;
                int allPrice = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()) {
                        num++;
                        int price = list.get(i).getPrice();
                        int count = list.get(i).getCount();
                        allPrice = allPrice + price * count;
                    }
                }

                if (num == list.size()) {//全选
                    mChecketBox.setChecked(true);
                } else {//不是全选
                    mChecketBox.setChecked(false);
                }

                String html = "合计：" + "<font color='#d43c3c'>" + allPrice + "</font>";
                mPrice.setText(Html.fromHtml(html));

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        //获取购物车数据
        doShopCar();
    }

    private void doShopCar() {
        Map<String, String> headMap = new HashMap<>();
        headMap.put("userId", SharedUtils.getInt(getActivity(), "userid") + "");
        headMap.put("sessionId", SharedUtils.getString(getActivity(), "sessionid"));
        new OkHttpUtils().setHead(headMap).get(Http.HTTP_URL_SHOP_CAR).result(new OkHttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                Log.i("ShopCarFragment", data);
                ShopCarBean shopCarBean = new Gson().fromJson(data, ShopCarBean.class);
                resultShopCar = shopCarBean.getResult();
                mShopCarAdapter.setList(resultShopCar);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }
}
