package com.languang.bluebox.presenter;

import com.mrj.framworklib.utils.OkHttpCallBack;

public interface IFacilityList {

    /**
     * 刷新
     *
     * @param callBack
     */
    void refreshList(OkHttpCallBack callBack);

    /**
     * 添加box
     *
     * @param uid
     * @param pwd
     * @param callBack
     */
    void insertBox(String uid, String pwd, OkHttpCallBack callBack);

    /**
     * 删除box
     *
     * @param uid
     * @param callBack
     */
    void deleteBox(String uid, OkHttpCallBack callBack);

    /**
     * 设置box昵称
     *
     * @param uid
     * @param nick
     * @param callBack
     */
    void setBoxNick(String uid, String nick, OkHttpCallBack callBack);

    /**
     * 获取box列表
     *
     * @param callBack
     */
    void getBoxList(OkHttpCallBack callBack);

    /**盒子登录
     * @param token
     * @param imei
     * @param pwdMd5
     * @param mobile
     * @param callBack
     */
    void boxLogin(String token, String imei, String pwdMd5, String mobile,OkHttpCallBack callBack);

}
