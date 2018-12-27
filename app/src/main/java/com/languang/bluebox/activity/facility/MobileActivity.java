package com.languang.bluebox.activity.facility;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.login.LoginActivity;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.entity.SpeRes;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MobileActivity extends BaseFragmentActivity {
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.name)
    TextView name;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mobile;
    }

    @Override
    protected void initView() {
        setTitle("绑定手机");
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>(1);
                params.put("mobile", mobile.getText()
                        .toString());
                params.put("name", name.getText()
                        .toString());
                params.put("token", MMKV.defaultMMKV()
                        .decodeString("token"));
                OkHttpUtils.getInstance()
                        .okPost(MobileActivity.this, TimeUtils.getGateway(MobileActivity.this) + "/setbox", null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                OkHttpUtils.getInstance()
                                        .okPost(MobileActivity.this, ApiConstant.CLOUD_WLAN_INFO, null, new OkHttpCallBack() {
                                            @Override
                                            public void onSucceed(String requestUrl, String response) {
                                                Log.d("ccnb", response);
                                                SpeRes speRes = new Gson().fromJson(response, SpeRes.class);
                                                if (speRes.getRet() == 200) {
                                                    Intent intent = new Intent(MobileActivity.this, LoginActivity.class);
                                                    startActivity(intent);
                                                }
                                            }

                                            @Override
                                            public void onFailed() {
                                            }
                                        });
                            }

                            @Override
                            public void onFailed() {
                                Log.d("ccnb", "fail");
                            }
                        });
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
