package com.example.month.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.month.R;
import com.example.month.bean.HomeBaseListBean;
import com.example.month.bean.HomeListBean;

import java.util.ArrayList;
import java.util.List;


public class HomeListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<HomeBaseListBean> list = new ArrayList<>();

    public HomeListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int id = getItemViewType(i);
        RecyclerView.ViewHolder viewHolder = null;
        Log.i("getItemViewType", id + "===");
        switch (id) {
            case 0://热销新品
                View view = View.inflate(context, R.layout.adapter_home_list_01, null);
                viewHolder = new RxxpViewHolder(view);
                break;
            case 1://魔力时尚
                View view1 = View.inflate(context, R.layout.adapter_home_list_02, null);
                viewHolder = new MLSSViewHolder(view1);
                break;
            case 2://品质生活
                View view2 = View.inflate(context, R.layout.adapter_home_list_01, null);
                viewHolder = new RxxpViewHolder(view2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof RxxpViewHolder && list.get(i) instanceof HomeListBean.ResultBean.RxxpBean) {
            RxxpViewHolder viewHolderRxxp = (RxxpViewHolder) viewHolder;
            HomeListBean.ResultBean.RxxpBean rxxpBean = (HomeListBean.ResultBean.RxxpBean) list.get(i);
            viewHolderRxxp.mTextTitle.setText(rxxpBean.getName());
            HomeListAdapter_RxxpAdapter mHomeListAdapter_RxxpAdapter = new HomeListAdapter_RxxpAdapter(context);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolderRxxp.mRecyclerRxxp.setLayoutManager(linearLayoutManager);
            viewHolderRxxp.mRecyclerRxxp.setAdapter(mHomeListAdapter_RxxpAdapter);

            //传递数据
            mHomeListAdapter_RxxpAdapter.setList(rxxpBean.getCommodityList());
        } else if (viewHolder instanceof MLSSViewHolder && list.get(i) instanceof HomeListBean.ResultBean.MlssBean) {
            MLSSViewHolder mlssViewHolder = (MLSSViewHolder) viewHolder;
            HomeListBean.ResultBean.MlssBean mlssBean = (HomeListBean.ResultBean.MlssBean) list.get(i);
            mlssViewHolder.mTextTitle.setText(mlssBean.getName());

            HomeListAdapter_MlssAdapter homeListAdapter_mlssAdapter = new HomeListAdapter_MlssAdapter(context);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mlssViewHolder.mRecyclerMlss.setLayoutManager(linearLayoutManager);
            mlssViewHolder.mRecyclerMlss.setAdapter(homeListAdapter_mlssAdapter);
            homeListAdapter_mlssAdapter.setList(mlssBean.getCommodityList());

        }
    }

    @Override
    public int getItemViewType(int i) {
        if (list.get(i) instanceof HomeListBean.ResultBean.RxxpBean) {
            return 0;
        } else if (list.get(i) instanceof HomeListBean.ResultBean.MlssBean) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setList(List<HomeBaseListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    //热销新品
    private class RxxpViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mRecyclerRxxp;
        TextView mTextTitle;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.home_list_01_text);
            mRecyclerRxxp = (RecyclerView) itemView.findViewById(R.id.recycler_rxxp);
        }
    }

    //魔力时尚
    private class MLSSViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mRecyclerMlss;
        TextView mTextTitle;

        public MLSSViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.home_list_01_text);
            mRecyclerMlss = (RecyclerView) itemView.findViewById(R.id.recycler_mlss);
        }
    }
}
