package com.example.month.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.example.month.R;
import com.example.month.base.BaseAdapter;
import com.example.month.bean.ShopCarBean;
import com.example.month.view.ShopAddView;

import java.util.List;

/**
 * 购物车适配器
 */
public class ShopCarAdapter extends BaseAdapter<ShopCarBean.ResultBean> {
    public ShopCarAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindData(final ShopCarBean.ResultBean resultBean, final int i, BaseViewHolder baseViewHolder) {
        baseViewHolder.setText(resultBean.getCommodityName(), R.id.tv_shop_title);
        baseViewHolder.setText(resultBean.getPrice() + "", R.id.tv_shop_price);
        baseViewHolder.setPicTrue(resultBean.getPic(), R.id.shop_iamge);
        ShopAddView mShopAddView = (ShopAddView) baseViewHolder.get(R.id.shop_add_view);
        mShopAddView.setCount(resultBean.getCount());

        mShopAddView.setOnNumListener(new ShopAddView.OnNumListener() {
            @Override
            public void count(int count) {
                resultBean.setCount(count);
                List<ShopCarBean.ResultBean> list = getList();

                Log.i("ShopCarAdapter",list.toString());

                if(mOnCallBackDataListener!=null){
                    mOnCallBackDataListener.changeData(list);
                }
            }
        });

        CheckBox mChecketBox = (CheckBox) baseViewHolder.get(R.id.checkbox);
        mChecketBox.setChecked(resultBean.isChecked());//设置是否选中

        mChecketBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("ShopCarAdapter",isChecked+"");
                resultBean.setChecked(isChecked);//改变数据
                List<ShopCarBean.ResultBean> list = getList();

                Log.i("ShopCarAdapter",list.toString());

                if(mOnCallBackDataListener!=null){
                    mOnCallBackDataListener.changeData(list);
                }

            }
        });


    }



    @Override
    public int getLayoutId() {
        return R.layout.adapter_shop_car;
    }

    private OnCallBackDataListener mOnCallBackDataListener;

    public void setOnCallBackDataListener(OnCallBackDataListener mOnCallBackDataListener) {
        this.mOnCallBackDataListener = mOnCallBackDataListener;
    }

    public interface OnCallBackDataListener {
        void changeData(List<ShopCarBean.ResultBean> list);
    }
}
