package com.languang.bluebox.model;

import android.content.Context;

import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.Map;

import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.presenter.ILogin;

/**
 * 请求识别宝盒
 *
 * @author 49829
 * @date 2018/4/9
 */

public class LoginModel implements ILogin {

    private Context context;

    public LoginModel(Context context) {
        this.context = context;
    }

    @Override
    public void matchBox(String url, Map<String, Object> params, OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okPost(context, url, params, callBack);
    }

    @Override
    public void login(String url, Map<String, Object> params, OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okPost(context, url, params, callBack);
    }
}
