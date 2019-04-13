package com.languang.bluebox.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.utils.MyUtil;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.tencent.mmkv.MMKV;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.button.ButtonView;

import java.util.HashMap;

public class ForgetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XUI.initTheme(this);
        setContentView(R.layout.forget);
        TextView hostName = findViewById(R.id.hostName);
        TextView type = findViewById(R.id.type);
        TextView status = findViewById(R.id.status);
        TextView sn = findViewById(R.id.sn);
        TextView net = findViewById(R.id.net);
        TextView re = findViewById(R.id.forget);
        ButtonView next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils.getInstance()
                        .okPost(ForgetActivity.this, ApiConstant.WLAN_SYS_LOGIN, new HashMap<String, Object>() {{
                            put("type", "scode");
                            put("imei",MyUtil.getImei(ForgetActivity.this));
                        }}, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                Log.d("cc", response);
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
            }
        });
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        NetPort netPort = new Gson().fromJson(MMKV.defaultMMKV()
                .decodeString("user"), NetPort.class);
        hostName.setText(netPort.getHostname());
        type.setText(netPort.getModel());
        status.setText(netPort.isActivate() == true ? "已激活" : "未激活");
        sn.setText(netPort.getSn());
        net.setText(netPort.getIp());
    }
}
