package com.languang.bluebox;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.ResponseMessage;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;
import java.util.Map;

public class SettingPassWordActivity extends BaseFragmentActivity {
    TextView next;
    EditText password1;
    EditText edit;
    LoadingPopView loadingPopView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_settting_pwd;
    }

    @Override
    protected void initView() {
        loadingPopView = new LoadingPopView(this);
        next = findViewById(R.id.next);
        password1 = findViewById(R.id.pass1);
        edit = findViewById(R.id.edit);
        setTitle("连接到设备");

    }

    @Override
    protected void initData() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPopView.show();
                Map<String, Object> params = new HashMap<>(1);
                params.put("admin", password1.getText()
                        .toString());
                params.put("guest", edit.getText()
                        .toString());
                params.put("token", MMKV.defaultMMKV()
                        .decodeString("token"));
                OkHttpUtils.getInstance()
                        .okPost(SettingPassWordActivity.this, TimeUtils.getGateway() + "/setbox", null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                ResponseMessage<BoxBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<BoxBean>>() {
                                }.getType());
                                if (responseMessage.getData()
                                        .getStatus()
                                        .equals("9999")) {
                                    finish();
                                }
                            }

                            @Override
                            public void onFailed() {
                                loadingPopView.dissmiss();
                            }
                        });
            }
        });
    }
    /**
     * 初始化密码
     */
}
