package com.languang.bluebox.activity.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.LookForPasswordActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SharedPrefsUtil;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.SpeRes;
import com.languang.bluebox.entity.login.LoginRes;
import com.languang.bluebox.model.LoginModel;
import com.languang.bluebox.presenter.ILogin;
import com.languang.bluebox.utils.MyUtil;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
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
    @BindView(R.id.sms_login)
    TextView sms_login;
    @BindView(R.id.find_pwd)
    TextView find_pwd;
    private ILogin model;
    boolean issms = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
//        Log.d("lsy", TimeUtils.getWlanIp());
        userPhoneEt.setText("15801653609");
        pwdEt.setText("123");
        find_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (issms) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("mobile", userPhoneEt.getText()
                            .toString());
                    map.put("type", "login");
                    OkHttpUtils.getInstance()
                            .okPost(LoginActivity.this, ApiConstant.SEND_SMS_URL, map, new OkHttpCallBack() {
                                @Override
                                public void onSucceed(String requestUrl, String response) {
                                    SpeRes<MesBean> m = new Gson().fromJson(response, new TypeToken<SpeRes<MesBean>>() {
                                    }.getType());
                                    Toast.makeText(LoginActivity.this, m.getData()
                                            .getMsg(), Toast.LENGTH_SHORT)
                                            .show();
                                }

                                @Override
                                public void onFailed() {
                                }
                            });
                } else {
                    Intent intent = new Intent(LoginActivity.this, LookForPasswordActivity.class);
                    startActivity(intent);
                }
            }
        });
        sms_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (issms) {
                    sms_login.setText("账户密码登录");
                    pwdEt.setHint("请输入密码");
                    find_pwd.setText("找回密码");
                } else {
                    sms_login.setText("短信验证码登录");
                    pwdEt.setHint("请输入短信验证码");
                    find_pwd.setText("发送验证码");
                }
                pwdEt.setText("");
                issms = !issms;
            }
        });
//        sms_login.setText();
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
//    private void matchBox(String pwd) {
//        Map<String, Object> params = new HashMap<>(1);
//        params.put("password", pwd);
//        model.matchBox(ApiConstant.MATCH_BLUE_BOX_URL, params, this);
//    }

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
//        ApiConstant.WLAN_SYS_LOGIN
        if (issms) {
            params.put("type", "yzm");
        } else {
            params.put("type", "pwd");
        }
        params.put("imei", MyUtil.getImei(this));
        model.matchBox(ApiConstant.LOGIN_URL, params, this);
//        model.matchBox(ApiConstant.WLAN_SYS_LOGIN, params, this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        Log.d("ccnb1111", response);
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
        Log.d("ccnb", "11");
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
