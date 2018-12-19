package com.languang.bluebox.model;

import android.content.Context;

import com.mrj.framworklib.utils.OkHttpCallBack;

import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.presenter.IDiskManageInfo;

/**
 * 获取硬盘信息实现类
 *
 * @author 49829
 * @date 2018/4/9
 */

public class DiskManageInfoModel implements IDiskManageInfo {

    private Context context;

    public DiskManageInfoModel(Context context) {
        this.context = context;
    }

    @Override
    public void getDiskManageInfo(String url, OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okGet(context, url, callBack);
    }
}
