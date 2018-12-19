package com.languang.bluebox.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.model.RegisterModel;
import com.languang.bluebox.presenter.IRegister;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手机注册页面
 *
 * @author mrj
 * 2018/12/2 14:23
 */
public class RegisterActivity extends BaseFragmentActivity implements OkHttpCallBack {
    @BindView(R.id.user_phone_et)
    EditText userPhoneEt;
    @BindView(R.id.code_et)
    EditText codeEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.consume_pwd_et)
    EditText consumePwdEt;

    private IRegister registerModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        registerModel = new RegisterModel(this);
    }

    @OnClick({R.id.get_code, R.id.register_submit, R.id.back_to_login})
    public void onViewClicked(View view) {
        String mobile = userPhoneEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        String consumePwd = consumePwdEt.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtilsBase.showToastCenter(this, "手机号不能为空");
            return;
        }
        switch (view.getId()) {
            case R.id.get_code:
                registerModel.getCode(mobile, this);
                break;
            case R.id.register_submit:

                if (TextUtils.isEmpty(pwd)) {
                    ToastUtilsBase.showToastCenter(this, "密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(consumePwd)) {
                    ToastUtilsBase.showToastCenter(this, "确认密码不能为空");
                    return;
                }
                if (!pwd.equals(consumePwd)) {
                    ToastUtilsBase.showToastCenter(this, "两次密码输入不一致");
                    return;
                }
                registerModel.register(mobile, pwd, this);
                break;
            case R.id.back_to_login:
                finish();
                break;
        }
    }

    @Override
    public void onSucceed(String requestUrl, String response) {

    }

    @Override
    public void onFailed() {
        ToastUtilsBase.showToastCenter(this, "网络请求失败，请重试");
    }
}
