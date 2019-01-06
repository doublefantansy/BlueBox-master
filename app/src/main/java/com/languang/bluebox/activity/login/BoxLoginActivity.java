package com.languang.bluebox.activity.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.initialize.SettingPwdActivity;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SharedPrefsUtil;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.login.LoginRes;
import com.languang.bluebox.utils.MyUtil;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class BoxLoginActivity extends BaseFragmentActivity {
    //    @BindView(R.id.user_phone_et)
//    EditText userPhoneEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.login_submit)
    Button login;

    //    login_submit
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login1;
    }

    @Override
    protected void initView() {
//        userPhoneEt.setText("15801653609");
//        pwdEt.setText("111");
    }

    @Override
    protected void initData() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>(1);
//        if (isActive) {
                params.put("type", "pwd");
                params.put("pwd", pwdEt.getText()
                        .toString());
//                params.put("mobile", userPhoneEt.getText()
//                        .toString());
                params.put("imei", MyUtil.getImei(BoxLoginActivity.this));
//        } else {
                OkHttpUtils.getInstance()
                        .okPost(BoxLoginActivity.this, TimeUtils.getGateway(BoxLoginActivity.this) + "/syslogin", params, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                Log.d("ccnb", requestUrl + response);
                                ResponseMessage<LoginRes> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<LoginRes>>() {
                                }.getType());
                                LoginRes loginRes = responseMessage.getData();
                                if (loginRes.getStatus()
                                        .equals("9999")) {
                                    SharedPrefsUtil.putValue("InitialInfo", "AccessToken", responseMessage.getData()
                                            .getToken());
//                            if (loginRes.getToken() == null) {
                                    Intent intent = new Intent(BoxLoginActivity.this, SettingPwdActivity.class);
                                    startActivity(intent);
                                } else if (loginRes.getStatus()
                                        .equals("40")) {
                                    Toast.makeText(BoxLoginActivity.this, "管理密码错误", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    Toast.makeText(BoxLoginActivity.this, "未知错误", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
            }
        });
    }
}
