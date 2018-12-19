package com.languang.bluebox.presenter;

import com.mrj.framworklib.utils.OkHttpCallBack;

/**
 * 设备信息
 *
 * @author 49829
 * @date 2018/4/8
 */

public interface IFacilityInfo {
    /**
     * 请求设备信息数据
     *
     * @param url      地址
     * @param callBack 回调
     */
    void getFacilityInfo(String url, OkHttpCallBack callBack);
}
