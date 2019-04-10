package com.example.month.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.month.R;
import com.example.month.adapter.HomeListAdapter;
import com.example.month.base.BaseFragment;
import com.example.month.bean.BannerBean;
import com.example.month.bean.HomeBaseListBean;
import com.example.month.bean.HomeListBean;
import com.example.month.utils.Http;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    private XBanner mXBanner;
    private RecyclerView mRecyclerView;

    private List<HomeBaseListBean> resultBeanList = new ArrayList<>();
    private HomeListAdapter homeListAdapter;

    @Override
    protected void initView(View viewChild) {
        mXBanner = (XBanner) get(R.id.xbanner);
        mRecyclerView = (RecyclerView) get(R.id.recycler);
    }

    @Override
    protected void initData() {
        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                BannerBean.ResultBean bean = (BannerBean.ResultBean) model;
                Glide.with(getActivity()).load(bean.getImageUrl()).into((ImageView) view);
            }
        });

        homeListAdapter = new HomeListAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(homeListAdapter);

        getPresnter().doGetHttp(0, Http.HTTP_URL_BANNER);
        getPresnter().doGetHttp(1, Http.HTTP_URL_HOME_SHOP_LIST);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 0) {//Banner请求
            BannerBean bean = new Gson().fromJson(data, BannerBean.class);
            mXBanner.setBannerData(bean.getResult());
        } else if (type == 1) {//首页商品列表获取
            HomeListBean bean = new Gson().fromJson(data, HomeListBean.class);
            HomeListBean.ResultBean result = bean.getResult();

            HomeListBean.ResultBean.RxxpBean rxxpBean = result.getRxxp();//热销新品的bean

            HomeListBean.ResultBean.MlssBean mlssBean = result.getMlss();//魔力时尚的bean

            HomeListBean.ResultBean.PzshBean pzshBean = result.getPzsh();//品质生活的bean

            resultBeanList.add(rxxpBean);

            resultBeanList.add(mlssBean);

            resultBeanList.add(pzshBean);

            homeListAdapter.setList(resultBeanList);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
