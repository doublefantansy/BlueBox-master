package com.languang.bluebox.presenter;

import com.mrj.framworklib.utils.OkHttpCallBack;

public interface ILightDisk {
    /**
     * 开始归档
     */
    void startArchive(OkHttpCallBack callBack);

    /**
     * 归档进度
     */
    void archiveProgress(OkHttpCallBack callBack);
}
