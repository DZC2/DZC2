package com.example.month.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 万能适配器
 */
public abstract class BaseAdapter<D> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {
    private Context context;
    private List<D> mList=new ArrayList<>();

    public BaseAdapter(Context context) {
        this.context = context;
    }

    //传递数据
    public void setList(List<D> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }

    //获取数据
    public List<D> getList(){
        return mList;
    }

    @NonNull
    @Override
    public BaseAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, getLayoutId(), null);
        BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder baseViewHolder, int i) {
        bindData(mList.get(i),i,baseViewHolder);
    }


    //由子类去实现的方法
    protected abstract void bindData(D d,int i, BaseViewHolder baseViewHolder);

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }

        private SparseArray<View> sparseArray = new SparseArray<>();

        //根据id 寻找对应的控件
        public View get(int id) {
            View view = sparseArray.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                sparseArray.put(id, view);
            }
            return view;
        }

        //给TextView设置数据
        public void setText(String content,int id){
            ((TextView)get(id)).setText(content);
        }

        //给ImageView设置图片
        public void setPicTrue(String url,int id){
            ImageView imageView=(ImageView) get(id);
            Glide.with(context).load(url).into(imageView);
        }


    }

    //子类传递的layout 视图
    public abstract int getLayoutId();
}
