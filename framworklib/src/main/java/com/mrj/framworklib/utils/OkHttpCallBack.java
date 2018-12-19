package com.mrj.framworklib.utils;

/**
 * 网络请求回调
 *
 * @author 49829
 * @date 2017/11/27
 */

public interface OkHttpCallBack {
    /**
     * 请求成功
     *@param requestUrl 请求地址
     * @param response 返回的信息
     */
    void onSucceed(String requestUrl,String response);

    /**
     * 请求失败
     */
    void onFailed();
}
