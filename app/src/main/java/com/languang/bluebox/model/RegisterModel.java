package com.languang.bluebox.model;

import android.content.Context;

import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.presenter.IRegister;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.HashMap;
import java.util.Map;

public class RegisterModel implements IRegister {
    private Context context;

    public RegisterModel(Context context) {
        this.context = context;
    }

    @Override
    public void getCode(String mobile, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("mobile", mobile);
        params.put("type", "signup");
        OkHttpUtils.getInstance().okPost(context, ApiConstant.CLOUD_SENSMS, params, callBack);
    }

    @Override
    public void register(String mobile, String pwd, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("mobile", mobile);
        params.put("pwd", pwd);
        OkHttpUtils.getInstance().okPost(context, ApiConstant.CLOUD_SIGNUP, params, callBack);
    }
}
