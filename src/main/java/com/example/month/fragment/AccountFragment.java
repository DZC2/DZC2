package com.example.month.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.month.R;
import com.example.month.activity.LoginActivity;
import com.example.month.base.BaseFragment;
import com.example.month.utils.SharedUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

/**
 * 我的页面
 */
public class AccountFragment extends BaseFragment {
    private TextView mNickName;
    private ImageView mPicture;

    @Override
    protected void initView(View viewChild) {
        mNickName = (TextView) get(R.id.account_nickname);
        mPicture = (ImageView) get(R.id.account_picture);
    }

    @Override
    protected void initData() {
        setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.account_picture:
                        int userid = SharedUtils.getInt(getActivity(), "userid");
                        if (userid != 0) {//已经登录
                            //跳转到个人资料页面

                        } else {//没有登录
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                        break;
                }
            }
        }, R.id.account_picture);
    }

    @Override
    public void onResume() {
        super.onResume();

        //判断  用户是否登录
        int userid = SharedUtils.getInt(getActivity(), "userid");
        if (userid != 0) {//已经登录
            mNickName.setText(SharedUtils.getString(getActivity(), "nickname"));
            Glide.with(getActivity()).load(SharedUtils.getString(getActivity(), "headpic"))
                    .apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mPicture);
        } else {//未登录
            mNickName.setText("请登录");
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_account;
    }
}
