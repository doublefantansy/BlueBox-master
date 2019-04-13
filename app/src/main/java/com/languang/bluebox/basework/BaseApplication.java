package com.languang.bluebox.basework;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.util.LogWriter;

import com.blankj.utilcode.util.Utils;
import com.languang.bluebox.LogFiles;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.bugly.Bugly;
import com.tencent.mmkv.MMKV;
import com.xuexiang.xui.XUI;

import cn.finalteam.rxgalleryfinal.utils.ModelUtils;

/**
 * 初始配置
 *
 * @author mrj
 * @date 2017/11/23
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication application;
    private LogWriter mLogWriter;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
        MMKV.initialize(this);
        application = this;
        ModelUtils.setDebugModel(true);
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoader.getInstance()
                .init(config.build());
        Bugly.init(getApplicationContext(), "fcfd6d8984", false);
        Utils.init(this);
        LogFiles.init();
    }
}
