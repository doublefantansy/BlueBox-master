package com.languang.bluebox.presenter;

import com.mrj.framworklib.utils.OkHttpCallBack;

/**
 *硬盘信息
 * @author 49829
 * @date 2018/4/9
 */

public interface IDiskManageInfo {
    /**
     * 获取硬盘信息
     * @param url 地址
     * @param callBack 回调
     */
    void getDiskManageInfo(String url, OkHttpCallBack callBack);
}
