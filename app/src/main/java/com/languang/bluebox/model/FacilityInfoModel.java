package com.languang.bluebox.model;

import android.content.Context;

import com.mrj.framworklib.utils.OkHttpCallBack;

import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.presenter.IFacilityInfo;

/**
 * 获取设备信息实现类
 *
 * @author 49829
 * @date 2018/4/8
 */

public class FacilityInfoModel implements IFacilityInfo {

    private Context context;

    public FacilityInfoModel(Context context) {
        this.context = context;
    }

    @Override
    public void getFacilityInfo(String url, OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okGet(context, url, callBack);
    }
}
