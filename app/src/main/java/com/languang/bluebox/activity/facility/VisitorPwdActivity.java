package com.languang.bluebox.activity.facility;

import android.content.Intent;
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

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.ResponseMessage;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 游客密码
 *
 * @author 49829
 * @date 2018/3/26
 */

public class VisitorPwdActivity extends BaseFragmentActivity {
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.eye)
    ImageView eye;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_visitor_pwd;
    }

    @Override
    protected void initView() {
        setTitle("访客密码");
        setRightText("保存");
        setRightOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> params = new HashMap<>(2);
                params.put("guest", "abc");
                dialogUtil.showDialog("更新管理密码中...");
                OkHttpUtils.getInstance().okPost(mContext, ApiConstant.SETTING_PWD_URL, params, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        dialogUtil.dismissDialog();
                        ResponseMessage responseMessage = new Gson().fromJson(response, ResponseMessage.class);
//                        ToastUtilsBase.showToastCenter(mContext, responseMessage.getMsg());
//                        if (Constant.REQUEST_SUCCEED.equals(responseMessage.getRet())) {
                        finish();
                        Intent intent = new Intent(mContext, MyWifiActivity.class);
                        intent.putExtra("pwd", "123456");
                        startActivity(intent);
//                        }
                    }

                    @Override
                    public void onFailed() {
                        dialogUtil.dismissDialog();
                    }
                });
            }
        });
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
