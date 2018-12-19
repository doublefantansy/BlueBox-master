package com.languang.bluebox.activity.facility;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;

import java.util.HashMap;
import java.util.Map;

import com.languang.bluebox.MainActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.ResponseMessage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的WIFI界面
 *
 * @author 49829
 * @date 2018/3/29
 */

public class MyWifiActivity extends BaseFragmentActivity implements OkHttpCallBack {

    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.eye)
    ImageView eye;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_wifi;
    }

    @Override
    protected void initView() {
        setTitle("我的Wi-Fi");
    }

    @Override
    protected void initData() {
        String pwd = getIntent().getStringExtra("pwd");
        if (null != pwd && !pwd.isEmpty()) {
            pwdEt.setText(pwd);
            pwdEt.setSelection(pwd.length() - 1);
        }
    }


    @Override
    public void onSucceed(String requestUrl, String response) {
        ResponseMessage responseMessage = new Gson().fromJson(response, ResponseMessage.class);
//        ToastUtilsBase.showToastCenter(mContext, responseMessage.getMsg());
//        if (Constant.REQUEST_SUCCEED.equals(responseMessage.getRet())) {
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
//        } else {
//        }
    }

    @Override
    public void onFailed() {

    }


    /**
     * 初始化密码
     */
    private void updateWifi() {
        Map<String, Object> params = new HashMap<>(2);
        params.put("name", "baohe360");
        params.put("password", "123456");
        params.put("hidden", true);
        OkHttpUtils.getInstance().okPost(mContext, ApiConstant.SETTING_PWD_URL, params, this);
    }


    @OnClick({R.id.eye, R.id.save_or_restart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eye:
                if (eye.isSelected()) {
                    eye.setSelected(false);
                    pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    eye.setSelected(true);
                    pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            case R.id.save_or_restart:
                updateWifi();
                break;
            default:
                break;
        }
    }
}
