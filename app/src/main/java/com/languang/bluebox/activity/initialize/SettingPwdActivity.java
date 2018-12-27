package com.languang.bluebox.activity.initialize;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.facility.AdminPwdActivity;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.NetPort;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化密码
 *
 * @author 49829
 * @date 2018/4/12
 */
public class SettingPwdActivity extends BaseFragmentActivity {
    TextView next;
    NetPort netPort;
    EditText password1;
    EditText edit;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_settting_pwd;
    }

    @Override
    protected void initView() {
        next = findViewById(R.id.next);
        TextView hostName = findViewById(R.id.hostName);
        TextView type = findViewById(R.id.type);
        TextView status = findViewById(R.id.status);
        TextView sn = findViewById(R.id.sn);
        TextView net = findViewById(R.id.net);
        password1 = findViewById(R.id.pass1);
        edit = findViewById(R.id.edit);
        setTitle("连接到设备");
        netPort = new Gson().fromJson(MMKV.defaultMMKV()
                .decodeString("user"), NetPort.class);
        hostName.setText(netPort.getHostname());
        type.setText(netPort.getModel());
        status.setText(netPort.isActivate() == true ? "已激活" : "未激活");
        sn.setText(netPort.getSn());
        net.setText(netPort.getIp());
//        setRightText("保存");
//        setRightOnclick(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updatePwd();
//            }
//        });
    }

    @Override
    protected void initData() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>(1);
                params.put("admin", password1.getText()
                        .toString());
                params.put("guest", edit.getText()
                        .toString());
                params.put("token", MMKV.defaultMMKV()
                        .decodeString("token"));
                OkHttpUtils.getInstance()
                        .okPost(SettingPwdActivity.this, TimeUtils.getGateway(SettingPwdActivity.this) + "/setbox", null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                Intent intent = new Intent(SettingPwdActivity.this, AdminPwdActivity.class);
                                intent.putExtra("pwd", password1.getText()
                                        .toString());
                                startActivity(intent);
                            }

                            @Override
                            public void onFailed() {
                                Log.d("ccnb", "f");
                            }
                        });
            }
        });
    }
    /**
     * 初始化密码
     */
}
