package com.example.month;


import com.example.month.adapter.MainPagerAdapter;
import com.example.month.base.BaseActivity;
import com.example.month.view.BottomTabView;
import com.example.month.view.NoScrollViewPager;


public class MainActivity extends BaseActivity {

    private int[] tabsYes = {
            R.drawable.tab_home_bottom_shouyes, R.drawable.tab_home_bottom_quanzis
            , R.drawable.tab_home_bottom_gouwuche, R.drawable.tab_home_bottom_zhangdans
            , R.drawable.tab_home_bottom_wodes

    };
    private int[] tabsNo = {
            R.drawable.tab_home_bottom_shouye, R.drawable.tab_home_bottom_quanzi
            , R.drawable.tab_home_bottom_gouwuche, R.drawable.tab_home_bottom_zhangdan
            , R.drawable.tab_home_bottom_wode

    };
    private BottomTabView mBottomTabView;
    private NoScrollViewPager mViewPager;

    @Override
    protected void initView() {
        mViewPager = (NoScrollViewPager) get(R.id.viewpager);
        mBottomTabView = (BottomTabView) get(R.id.bottom_view);
        mBottomTabView.setTab(tabsYes, tabsNo, 0);

        MainPagerAdapter mMainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMainPagerAdapter);

        mBottomTabView.bindTab(mViewPager);//绑定

        mViewPager.setOffscreenPageLimit(0);//取消预加载

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(int type, String json) {
        super.success(type, json);
        if (type == 0) {

        }
    }


}

