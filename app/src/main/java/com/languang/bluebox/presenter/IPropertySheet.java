package com.languang.bluebox.presenter;

import com.mrj.framworklib.utils.OkHttpCallBack;

import java.io.File;

public interface IPropertySheet {
    void getOutList(OkHttpCallBack callBack);

    void deleteOutFile(String[] files, OkHttpCallBack callBack);

    void startOut( String[] files, OkHttpCallBack callBack);

    void facilityList(String type,OkHttpCallBack callBack);

}
