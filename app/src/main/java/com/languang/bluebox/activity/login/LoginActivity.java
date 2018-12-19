package com.languang.bluebox.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.MainActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.utils.SharedPrefsUtil;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.login.LoginRes;
import com.languang.bluebox.model.LoginModel;
import com.languang.bluebox.presenter.ILogin;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录（初始化wifi界面）
 *
 * @author 49829
 * @date 2018/3/28
 */
public class LoginActivity extends BaseFragmentActivity implements OkHttpCallBack {
    @BindView(R.id.user_phone_et)
    EditText userPhoneEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    private ILogin model;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        userPhoneEt.setText("15801653609");
        pwdEt.setText("123");
    }

    @Override
    protected void initData() {
        model = new LoginModel(this);
    }

    /**
     * 识别宝盒
     *
     * @param pwd 密码
     */
    private void matchBox(String pwd) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("password", pwd);
        model.matchBox(ApiConstant.MATCH_BLUE_BOX_URL, params, this);
    }

    /**
     * 登录
     *
     * @param user 用户名
     * @param pwd  密码
     */
    private void login(String user, String pwd) {
        Map<String, Object> params = new HashMap<>(1);
//        params.put("mobile", user);
//        params.put("pwd", pwd);
        params.put("mobile", userPhoneEt.getText()
                .toString());
        params.put("pwd", pwdEt.getText()
                .toString());
        params.put("type", "pwd");
        model.matchBox(ApiConstant.CLOUD_LOGIN, params, this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        ResponseMessage<LoginRes> resResponseMessage = new Gson().fromJson(response,
                new TypeToken<ResponseMessage<LoginRes>>() {
                }.getType());
        if (Constant.SUCCEED_CODE == resResponseMessage.getRet() && resResponseMessage.getData()
                .getStatus()
                .equals("9999")) {
//            if (resResponseMessage.getData().)
            String token = SharedPrefsUtil.getValue("InitialInfo", "AccessToken", "");
            if (token.equals(resResponseMessage.getData()
                    .getToken())) {
                startActivity(new Intent(this, FacilityListActivity.class));
                finish();
            } else {
                SharedPrefsUtil.putValue("InitialInfo", "AccessToken", resResponseMessage.getData()
                        .getToken());
                startActivity(new Intent(this, FacilityListActivity.class));
                finish();
            }
        } else {
            ToastUtilsBase.showToastCenter(this, resResponseMessage.getData()
                    .getMsg());
        }
    }

    @Override
    public void onFailed() {
    }

    @OnClick({R.id.find_pwd, R.id.login_submit, R.id.to_register, R.id.sms_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find_pwd:
                break;
            case R.id.login_submit:
//                if (TextUtils.isEmpty(userPhoneEt.getText().toString())) {
//                    ToastUtilsBase.showToastCenter(this, "手机号不能为空");
//                    return;
//                }
//                if (TextUtils.isEmpty(pwdEt.getText().toString())) {
//                    ToastUtilsBase.showToastCenter(this, "密码不能为空");
//                    return;
//                }
                login(userPhoneEt.getText()
                        .toString(), pwdEt.getText()
                        .toString());
                break;
            case R.id.to_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.sms_login:
                break;
        }
    }
}
