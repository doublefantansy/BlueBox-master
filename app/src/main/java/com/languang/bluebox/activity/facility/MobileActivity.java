package com.languang.bluebox.activity.facility;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.languang.bluebox.LoadingPopView;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.SplashActivity;
import com.languang.bluebox.activity.login.LoginActivity;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.entity.ResponseMessage;
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
    LoadingPopView popView;
    boolean isBreak;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mobile;
    }

    @Override
    protected void initView() {
        popView = new LoadingPopView(this);
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
                popView.show();
                Map<String, Object> map = new HashMap<>();
                map.put("name", name.getText()
                        .toString());
                map.put("mobile", mobile.getText()
                        .toString());
                OkHttpUtils.getInstance()
                        .okPost(MobileActivity.this, TimeUtils.getGateway() + "/setbox", map, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                Log.d("cctag", response);
                                if (TimeUtils.getGateway()
                                        .equals("http://box.haotuwei.com")) {
                                    Intent intent = new Intent(MobileActivity.this, LoginActivity.class);
                                    MobileActivity.this.startActivity(intent);
                                    finish();
                                } else {
                                    OkHttpUtils.getInstance()
                                            .okPost(MobileActivity.this, TimeUtils.getGateway() + "/chongqi", null, new OkHttpCallBack() {
                                                @Override
                                                public void onSucceed(String requestUrl, String response) {
                                                    ResponseMessage responseMessage = new Gson().fromJson(response, ResponseMessage.class);
                                                    if (responseMessage.getRet() == 200) {
                                                        OkHttpUtils.getInstance()
                                                                .okPost(MobileActivity.this, TimeUtils.getGateway() + "/info", null, new OkHttpCallBack() {
                                                                    @Override
                                                                    public void onSucceed(String requestUrl, String response) {
                                                                        Intent intent = new Intent(MobileActivity.this, SplashActivity.class);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }

                                                                    @Override
                                                                    public void onFailed() {
                                                                        OkHttpUtils.getInstance()
                                                                                .okPost(MobileActivity.this, TimeUtils.getGateway() + "/info", null, new OkHttpCallBack() {
                                                                                    @Override
                                                                                    public void onSucceed(String requestUrl, String response) {
                                                                                        Intent intent = new Intent(MobileActivity.this, SplashActivity.class);
                                                                                        startActivity(intent);
                                                                                        finish();
                                                                                    }

                                                                                    @Override
                                                                                    public void onFailed() {
                                                                                        Toast.makeText(MobileActivity.this, "重启失败，请物理重启", Toast.LENGTH_SHORT)
                                                                                                .show();
                                                                                        popView.dissmiss();
                                                                                    }
                                                                                });
                                                                    }
                                                                });
                                                    }
                                                }

                                                @Override
                                                public void onFailed() {
                                                }
                                            });
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

    private void chongqi() {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
