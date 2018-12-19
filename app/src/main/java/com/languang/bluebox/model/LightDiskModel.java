package com.languang.bluebox.model;

import android.content.Context;

import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.presenter.ILightDisk;
import com.mrj.framworklib.utils.OkHttpCallBack;

public class LightDiskModel implements ILightDisk {

    private Context context;

    public LightDiskModel(Context context) {

        this.context = context;
    }

    @Override
    public void startArchive(OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okPost(context, ApiConstant.BOX_START_GUIDANG, null, callBack);
    }

    @Override
    public void archiveProgress(OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okPost(context, ApiConstant.BOX_GUIDANG, null, callBack);
    }
}
