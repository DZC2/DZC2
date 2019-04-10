package com.example.month.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.month.R;
import com.example.month.mvp.model.BaseModelIml;
import com.example.month.mvp.presenter.BasePresenterIml;
import com.example.month.mvp.view.BaseView;
import com.example.month.view.UltimateBar;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private RelativeLayout mLyoutTitle;
    private TextView mTitle;
    private BasePresenterIml basePresenterIml;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mLyoutTitle = (RelativeLayout) findViewById(R.id.layout_title);
        mTitle = (TextView) (TextView) findViewById(R.id.tv_title);

        UltimateBar.newImmersionBuilder()
                .applyNav(false)         // 是否应用到导航栏
                .build(this)
                .apply();

        RelativeLayout mLayoutChild = (RelativeLayout) findViewById(R.id.layout_child);

        //初始化子类视图
        View view = View.inflate(this, getLayoutId(), null);
        mLayoutChild.addView(view);
        basePresenterIml = new BasePresenterIml(new BaseModelIml(), this);
        initView();
        initData();

    }

    //获取Presnter
    public BasePresenterIml getPresnter() {
        return basePresenterIml;
    }

    private SparseArray<View> sparseArray = new SparseArray<>();

    //根据id 寻找对应的控件
    public View get(int id) {
        View view = sparseArray.get(id);
        if (view == null) {
            view = findViewById(id);
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
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //初始化视图 控件
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();


    //子类传递的layout
    public abstract int getLayoutId();

    //控制title是否显示
    public void showLayoutTitle() {
        mLyoutTitle.setVisibility(View.VISIBLE);
    }

    //设置标题
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void success(int type, String data) {

    }

    @Override
    public void fail(int type, String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenterIml.destory();
    }
}
