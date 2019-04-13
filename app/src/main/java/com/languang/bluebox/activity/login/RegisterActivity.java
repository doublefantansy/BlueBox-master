package com.languang.bluebox.activity.login;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.SpeRes;
import com.languang.bluebox.presenter.IRegister;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;
import com.tencent.mmkv.MMKV;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 手机注册页面
 *
 * @author mrj
 * 2018/12/2 14:23
 */
public class RegisterActivity extends BaseFragmentActivity {
    @BindView(R.id.user_phone_et)
    EditText userPhoneEt;
    @BindView(R.id.code_et)
    EditText codeEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.consume_pwd_et)
    EditText consumePwdEt;
    @BindView(R.id.xieyi)
    TextView xieyi;
    @BindView(R.id.bar)
    TitleBar bar;
    private IRegister registerModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    protected void initData() {
        bar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.get_code, R.id.register_submit, R.id.back_to_login})
    public void onViewClicked(View view) {
        String mobile = userPhoneEt.getText()
                .toString();
        String pwd = pwdEt.getText()
                .toString();
        String consumePwd = consumePwdEt.getText()
                .toString();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtilsBase.showToastCenter(this, "手机号不能为空");
            return;
        }
        switch (view.getId()) {
            case R.id.get_code:
                Map<String, Object> map = new HashMap<>();
                map.put("mobile", userPhoneEt.getText()
                        .toString());
                map.put("type", "signup");
                OkHttpUtils.getInstance()
                        .okPost(RegisterActivity.this, ApiConstant.SEND_SMS_URL, map, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                SpeRes<MesBean> m = new Gson().fromJson(response, new TypeToken<SpeRes<MesBean>>() {
                                }.getType());
                                Toast.makeText(RegisterActivity.this, m.getData()
                                        .getMsg(), Toast.LENGTH_SHORT)
                                        .show();
//                                Toast.makeText(RegisterActivity.this, m.getData()
//                                        .getMsg(), Toast.LENGTH_SHORT)
//                                        .show();
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
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
                Map<String, Object> map1 = new HashMap<>();
                map1.put("mobile", userPhoneEt.getText()
                        .toString());
                map1.put("pwd", codeEt.getText()
                        .toString());
                OkHttpUtils.getInstance()
                        .okPost(RegisterActivity.this, ApiConstant.CLOUD_SIGNUP, map1, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                Log.d("ccnb", response);
                                SpeRes<RegBean> speRes = new Gson().fromJson(response, new TypeToken<SpeRes<RegBean>>() {
                                }.getType());
                                if (speRes.getData()
                                        .getState()
                                        .equals("0")) {
                                    MMKV.defaultMMKV()
                                            .encode("Token1", speRes.getData()
                                                    .getToken());
                                    Map<String, Object> map2 = new HashMap<>();
                                    map2.put("mobile", userPhoneEt.getText()
                                            .toString());
                                    map2.put("pwd", consumePwdEt.getText()
                                            .toString());
                                    OkHttpUtils.getInstance()
                                            .okPost1(RegisterActivity.this, ApiConstant.SET_PWD_URL, map2, new OkHttpCallBack() {
                                                @Override
                                                public void onSucceed(String requestUrl, String response) {
                                                    finish();
                                                }

                                                @Override
                                                public void onFailed() {
                                                }
                                            });
                                } else {
                                    Toast.makeText(RegisterActivity.this, speRes.getData()
                                            .getMsg(), Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
//                registerModel.register(mobile, pwd, this);
                break;
            case R.id.back_to_login:
                finish();
                break;
        }
    }
}
