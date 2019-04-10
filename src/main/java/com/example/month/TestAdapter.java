package com.example.month;

import android.content.Context;

import com.example.month.ListBean;
import com.example.month.base.BaseAdapter;


public class TestAdapter extends BaseAdapter<ListBean>{
    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindData(ListBean listBean,int i, BaseAdapter.BaseViewHolder baseViewHolder) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
