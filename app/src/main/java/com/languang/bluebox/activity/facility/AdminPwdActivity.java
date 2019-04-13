package com.languang.bluebox.activity.facility;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.R;
import com.languang.bluebox.activity.initialize.SettingWanActivity;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.entity.NetPort;
import com.tencent.mmkv.MMKV;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 管理密码
 *
 * @author 49829
 * @date 2018/3/26
 */
public class AdminPwdActivity extends BaseFragmentActivity {
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.eye)
    ImageView eye;
    @BindView(R.id.next)
    TextView next;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_admin_pwd;
    }

    @Override
    protected void initView() {
        final String pwd = getIntent().getStringExtra("pwd");
        setTitle("连接到设备");
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
                if (pwd.equals(pwdEt.getText()
                        .toString())) {
                    Intent intent = new Intent(AdminPwdActivity.this, SettingWanActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
//        setRightText("保存");
//        setRightOnclick(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String, Object> params = new HashMap<>(2);
//                params.put("admin", "123456");
//                dialogUtil.showDialog("更新管理密码中...");
//                OkHttpUtils.getInstance()
//                        .okPost(mContext, ApiConstant.SETTING_PWD_URL, params, new OkHttpCallBack() {
//                            @Override
//                            public void onSucceed(String requestUrl, String response) {
//                                dialogUtil.dismissDialog();
//                                finish();
//                                Intent intent = new Intent(mContext, MyWifiActivity.class);
//                                intent.putExtra("pwd", "123456");
//                                startActivity(intent);
////                        }
//                            }
//
//                            @Override
//                            public void onFailed() {
//                                dialogUtil.dismissDialog();
//                            }
//                        });
//            }
//        });
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.eye)
    public void onViewClicked() {
        if (eye.isSelected()) {
            eye.setSelected(false);
            pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            eye.setSelected(true);
            pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }
}
