package com.example.month.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.month.fragment.AccountFragment;
import com.example.month.fragment.ChatFragment;
import com.example.month.fragment.HomeFragment;
import com.example.month.fragment.OrderFragment;
import com.example.month.fragment.ShopCarFragment;

import java.util.ArrayList;
import java.util.List;


public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ChatFragment());
        fragmentList.add(new ShopCarFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new AccountFragment());
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
