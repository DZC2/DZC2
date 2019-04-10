package com.example.month.adapter;

import android.content.Context;

import com.example.month.R;
import com.example.month.base.BaseAdapter;
import com.example.month.bean.HomeListBean;


/**
 * 魔力时尚的适配器
 */
public class HomeListAdapter_MlssAdapter extends BaseAdapter<HomeListBean.ResultBean.MlssBean.CommodityListBeanXX> {
    public HomeListAdapter_MlssAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindData(HomeListBean.ResultBean.MlssBean.CommodityListBeanXX bean,int i, BaseViewHolder baseViewHolder) {
        baseViewHolder.setText(bean.getCommodityName(), R.id.mlss_title);

        baseViewHolder.setText("¥" + bean.getPrice(), R.id.mlss_price);

        baseViewHolder.setPicTrue(bean.getMasterPic(), R.id.mlss_image);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_mlss_item;
    }
}
