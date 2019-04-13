package com.languang.bluebox.activity.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.LoadingPopView;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.initialize.SettingWanActivity;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SharedPrefsUtil;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.login.LoginRes;
import com.languang.bluebox.utils.MyUtil;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.tencent.mmkv.MMKV;
import com.xuexiang.xui.widget.button.ButtonView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class BoxLoginActivity extends BaseFragmentActivity {
    //    @BindView(R.id.user_phone_et)
//    EditText userPhoneEt;
    @BindView(R.id.pass1)
    EditText pwdEt;
    @BindView(R.id.next)
    ButtonView login;
    @BindView(R.id.forget)
    TextView forget;
    //    PopupWindow popupWindow;
    LoadingPopView popView;

    //    login_submit
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login1;
    }

    @Override
    protected void initView() {
        popView = new LoadingPopView(this);
        TextView hostName = findViewById(R.id.hostName);
        TextView type = findViewById(R.id.type);
        TextView status = findViewById(R.id.status);
        TextView sn = findViewById(R.id.sn);
        TextView net = findViewById(R.id.net);
        NetPort netPort = new Gson().fromJson(MMKV.defaultMMKV()
                .decodeString("user"), NetPort.class);
        hostName.setText(netPort.getHostname());
        type.setText(netPort.getModel());
        status.setText(netPort.isActivate() == true ? "已激活" : "未激活");
        sn.setText(netPort.getSn());
        net.setText(netPort.getIp());
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BoxLoginActivity.this, ForgetActivity.class));
            }
        });
//        popupWindow = new PopupWindow();
//        popupWindow.setWidth(300);
//        popupWindow.setHeight(300);
//        View view = getLayoutInflater().inflate(R.layout.loading, null);
//        ImageView imageView1 = view.findViewById(R.id.iamge);
//        popupWindow.setContentView(view);
//        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView1, "rotation", 0, 360);
//        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        objectAnimator.setDuration(3000);
//        objectAnimator.setInterpolator(new LinearInterpolator());
//        final ImageView imageView = findViewById(R.id.logo);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                popupWindow.showAtLocation(getWindow()
////                        .getDecorView(), Gravity.CENTER, 0, 0);
////                objectAnimator.start();
////                finish();
//            }
//        });
//        userPhoneEt.setText("15801653609");
//        pwdEt.setText("111");
    }

    @Override
    protected void initData() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popView.show();
                Map<String, Object> params = new HashMap<>(1);
                params.put("type", "pwd");
                params.put("pwd", pwdEt.getText()
                        .toString());
                params.put("imei", MyUtil.getImei(BoxLoginActivity.this));
                OkHttpUtils.getInstance()
                        .okPost(BoxLoginActivity.this, TimeUtils.getGateway() + "/syslogin", params, new OkHttpCallBack() {
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
//
                                    Intent intent = new Intent(BoxLoginActivity.this, SettingWanActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else if (loginRes.getStatus()
                                        .equals("40")) {
                                    Toast.makeText(BoxLoginActivity.this, "管理密码错误", Toast.LENGTH_SHORT)
                                            .show();
                                    popView.dissmiss();
                                } else {
                                    Toast.makeText(BoxLoginActivity.this, "未知错误", Toast.LENGTH_SHORT)
                                            .show();
                                    popView.dissmiss();
                                }
                            }

                            @Override
                            public void onFailed() {
                                popView.dissmiss();
                            }
                        });
            }
        });
    }
}
