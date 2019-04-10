package com.example.month.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.month.R;
import com.example.month.base.BaseActivity;
import com.example.month.bean.LoginBean;
import com.example.month.utils.Http;
import com.example.month.utils.SharedUtils;
import com.example.month.utils.Utils;
import com.google.gson.Gson;

import okhttp3.FormBody;

//登录页面
public class LoginActivity extends BaseActivity {

    private EditText edPhone,edPass;

    @Override
    protected void onRestart() {
        super.onRestart();
        String temPhone = SharedUtils.getString(this, "tempPhone");
        if (!TextUtils.isEmpty(temPhone)) {
            edPhone.setText(temPhone);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedUtils.put(this,"tempPhone","");
    }

    @Override
    protected void initView() {
        edPhone = (EditText) get(R.id.login_phone);
        edPass = (EditText) get(R.id.login_pass);
    }

    @Override
    protected void initData() {
        setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.go_rigister://跳转到注册
                        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                        break;
                    case R.id.login:
                        doLogin();
                        break;
                }
            }
        }, R.id.go_rigister,R.id.login);
    }

    //登录
    private void doLogin() {
        String phone = edPhone.getText().toString().trim();
        String pass = edPass.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            toast("请输入您的手机号");
            return;
        }
        if (!Utils.isMobilePhone(phone)) {
            toast("请输入一个正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            toast("请输入您的密码");
            return;
        }

        pass=Utils.md5(pass);//MD5加密

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", phone);
        builder.add("pwd", pass);
        getPresnter().doPostHtpp(0, Http.HTTP_URL_LOGIN,builder);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        LoginBean bean=new Gson().fromJson(data, LoginBean.class);
        toast(bean.getMessage());
        if(bean.getStatus().equals("0000")){
            Log.i("LoginActivity",data);
            //保存数据  销毁返回上一页
            SharedUtils.put(this,"phone",bean.getResult().getPhone());
            SharedUtils.put(this,"nickname",bean.getResult().getNickName());
            SharedUtils.put(this,"headpic",bean.getResult().getHeadPic());
            SharedUtils.put(this,"sex",bean.getResult().getSex());
            SharedUtils.put(this,"userid",bean.getResult().getUserId());
            SharedUtils.put(this,"sessionid",bean.getResult().getSessionId());

            finish();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
}
