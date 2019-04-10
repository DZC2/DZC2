package com.example.month.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.example.month.R;
import com.example.month.base.BaseActivity;
import com.example.month.bean.BaseBean;
import com.example.month.utils.Http;
import com.example.month.utils.SharedUtils;
import com.example.month.utils.Utils;
import com.google.gson.Gson;

import okhttp3.FormBody;

//注册页面
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText edPhone, edCode, edPass;
    private String phone;

    @Override
    protected void initView() {
        edPhone = (EditText) get(R.id.register_phone);
        edCode = (EditText) get(R.id.register_code);
        edPass = (EditText) get(R.id.register_pass);

    }

    @Override
    protected void initData() {

        setOnClick(this, R.id.register, R.id.get_code, R.id.register_eye, R.id.go_login);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    private boolean isEye = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register://注册
                doRegister();
                break;
            case R.id.get_code://获取验证码

                break;
            case R.id.register_eye://显示或隐藏密码
                if (isEye) {
                    edPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isEye = false;
                } else {
                    edPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isEye = true;
                }


                break;
            case R.id.go_login://去登录
                finish();
                break;
        }
    }

    //注册
    private void doRegister() {
        phone = edPhone.getText().toString().trim();
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

        //走注册
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", phone);
        builder.add("pwd", pass);
        getPresnter().doPostHtpp(0, Http.HTTP_URL_REGISTER, builder);

    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        BaseBean bean = new Gson().fromJson(data, BaseBean.class);
        toast(bean.getMessage());
        if (bean.getStatus().equals("0000")) {
            //跳到登录，并把手机号携带过去
            SharedUtils.put(this, "tempPhone", phone);
            finish();
        }
    }
}
