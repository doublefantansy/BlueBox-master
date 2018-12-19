package com.languang.bluebox.presenter;

import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.Map;

/**
 * 识别宝盒
 *
 * @author 49829
 * @date 2018/4/9
 */

public interface ILogin {
    /**
     * 识别宝盒
     *
     * @param url      地址
     * @param params   参数
     * @param callBack 回调
     */
    void matchBox(String url, Map<String, Object> params, OkHttpCallBack callBack);



    /**
     * 登录
     * @param url      地址login
     * @param params   参数
     * @param callBack 回调
     */
    void login(String url, Map<String, Object> params, OkHttpCallBack callBack);

}
