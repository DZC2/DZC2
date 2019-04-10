package com.example.month.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.month.R;
import com.example.month.activity.ShopCarDescActivity;
import com.example.month.base.BaseAdapter;
import com.example.month.bean.HomeListBean;


/**

 * date:2019/3/29
 * 热销新品适配器
 */
public class HomeListAdapter_RxxpAdapter extends BaseAdapter<HomeListBean.ResultBean.RxxpBean.CommodityListBean> {
    private Context context;

    public HomeListAdapter_RxxpAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void bindData(final HomeListBean.ResultBean.RxxpBean.CommodityListBean commodityListBean,
                            int i,BaseViewHolder baseViewHolder) {

        baseViewHolder.setPicTrue(commodityListBean.getMasterPic(), R.id.rxxp_img);

        baseViewHolder.setText(commodityListBean.getCommodityName(), R.id.rxxp_title);

        baseViewHolder.setText("¥" + commodityListBean.getPrice(), R.id.rxxp_price);

        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到商品详情页
                Intent intent = new Intent(context, ShopCarDescActivity.class);
                intent.putExtra("shopid", commodityListBean.getCommodityId() + "");
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.adapter_rxxp_item;
    }
}
