package com.languang.bluebox.presenter;

import com.mrj.framworklib.utils.OkHttpCallBack;

/**
 *登录页面接口
 * @author mrj
 * 2018/12/2 14:25
 */
public interface IRegister {
    void getCode(String mobile, OkHttpCallBack callBack);
    void register(String mobile,String pwd,OkHttpCallBack callBack);
}
