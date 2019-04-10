package com.example.month.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.month.R;
import com.example.month.mvp.model.BaseModelIml;
import com.example.month.mvp.presenter.BasePresenterIml;
import com.example.month.mvp.view.BaseView;


public abstract class BaseFragment extends Fragment implements BaseView {
    private BasePresenterIml basePresenterIml;
    private View viewChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_base, null);
        //用于添加子View的layout
        RelativeLayout mLayout = (RelativeLayout) view.findViewById(R.id.layout_child);
        //子类传递的视图
        viewChild = View.inflate(getActivity(), getLayoutId(), null);
        mLayout.addView(viewChild);
        initView(viewChild);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        basePresenterIml = new BasePresenterIml(new BaseModelIml(), this);
        initData();
    }

    //获取Presnter
    public BasePresenterIml getPresnter() {
        return basePresenterIml;
    }

    protected abstract void initView(View viewChild);

    //初始化方法
    protected abstract void initData();

    public abstract int getLayoutId();


    private SparseArray<View> sparseArray = new SparseArray<>();

    //根据id 寻找对应的控件
    public View get(int id) {
        View view = sparseArray.get(id);
        if (view == null) {
            view = viewChild.findViewById(id);
            sparseArray.put(id, view);
        }
        return view;
    }

    //设置控件的点击事件
    public void setOnClick(View.OnClickListener clickListener, int... ids) {
        if (ids == null) {
            return;
        }

        for (int id : ids) {
            get(id).setOnClickListener(clickListener);
        }
    }

    //toast弹出提示
    public void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(int type, String data) {

    }

    @Override
    public void fail(int type, String error) {

    }
}
