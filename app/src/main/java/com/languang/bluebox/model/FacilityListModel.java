package com.languang.bluebox.model;

import android.content.Context;
import android.util.Log;

import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.presenter.IFacilityList;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.HashMap;
import java.util.Map;

public class FacilityListModel implements IFacilityList {
    private Context context;

    public FacilityListModel(Context context) {
        this.context = context;
    }

    @Override
    public void refreshList(OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okPost(context, ApiConstant.CLOUD_REFRESH, null, callBack);
    }

    @Override
    public void insertBox(String uid, String pwd, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("blueuuid", uid);
        params.put("pwd", pwd);
        OkHttpUtils.getInstance().okPost(context, ApiConstant.CLOUD_BLUE_INSERT, params, callBack);
    }

    @Override
    public void deleteBox(String uid, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("blueuuid", uid);
        OkHttpUtils.getInstance().okPost(context, ApiConstant.CLOUD_BLUE_DEL, params, callBack);
    }

    @Override
    public void setBoxNick(String uid, String nick, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("blueuuid", uid);
        params.put("name", nick);
        OkHttpUtils.getInstance().okPost(context, ApiConstant.CLOUD_BLUE_SET, params, callBack);
    }

    @Override
    public void getBoxList(OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okPost(context, ApiConstant.CLOUD_BLUES, null, callBack);
    }

    @Override
    public void boxLogin(String token, String imei, String pwdMd5, String mobile, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("token", token);
        params.put("imei", imei);
        params.put("pwdmd5", pwdMd5);
        params.put("mobile", mobile);
        Log.d("ccnb",params+"");
        OkHttpUtils.getInstance().okPost(context, ApiConstant.BOX_LOGIN, params, callBack);
    }


}
