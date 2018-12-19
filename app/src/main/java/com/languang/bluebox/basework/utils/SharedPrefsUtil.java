package com.languang.bluebox.basework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.languang.bluebox.basework.BaseApplication;


/**
 * 偏好设置管理工具
 *
 * @author 49829
 */
public class SharedPrefsUtil {
    public final static String SETTING = "SharedPreferences";
    private static final Context context = BaseApplication.getInstance().getApplicationContext();

    /**
     * 使用SharedPreferences保存数据
     *
     * @param spName 文件名
     * @param key    键
     * @param value  值
     */
    public static void putValue(String spName, String key, int value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.commit();
    }


    /**
     * 使用SharedPreferences保存数据
     *
     * @param spName 文件名
     * @param key    键
     * @param value  值
     */
    public static void putValue(String spName, String key, Long value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putLong(key, value);

        sp.commit();
    }

    /**
     * 使用SharedPreferences保存数据
     *
     * @param spName 文件名
     * @param key    键
     * @param value  值
     */
    public static void putValue(String spName, String key, float value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putFloat(key, value);
        sp.commit();
    }

    /**
     * 使用SharedPreferences保存数据
     *
     * @param spName 文件名
     * @param key    键
     * @param value  值
     */
    public static void putValue(String spName, String key, boolean value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    /**
     * 使用SharedPreferences保存数据
     *
     * @param spName 文件名
     * @param key    键
     * @param value  值
     */
    public static void putValue(String spName, String key, String value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }

    /**
     * 使用SharedPreferences获取数据
     *
     * @param spName   文件名
     * @param key      键
     * @param defValue 默认值
     */
    public static int getValue(String spName, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    /**
     * 使用SharedPreferences获取数据
     *
     * @param spName   文件名
     * @param key      键
     * @param defValue 默认值
     */
    public static float getValue(String spName, String key, float defValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        float value = sp.getFloat(key, defValue);
        return value;
    }

    /**
     * 使用SharedPreferences获取数据
     *
     * @param spName   文件名
     * @param key      键
     * @param defValue 默认值
     */
    public static long getValue(String spName, String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        long value = sp.getLong(key, defValue);
        return value;
    }

    /**
     * 使用SharedPreferences获取数据
     *
     * @param spName   文件名
     * @param key      键
     * @param defValue 默认值
     */
    public static boolean getValue(String spName, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    /**
     * 使用SharedPreferences获取数据
     *
     * @param spName   文件名
     * @param key      键
     * @param defValue 默认值
     */
    public static String getValue(String spName, String key, String defValue) {
        //实例化SharedPreferences
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    /**
     * 使用SharedPreferences移除数据
     *
     * @param spName 文件名
     * @param key    键
     */
    public static void remove(String spName, String key) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.remove(key);
        //提交当前数据
        editor.commit();
    }

    /**
     * 使用SharedPreferences移除所有数据
     *
     * @param spName 文件名
     */
    public static void removeAll(String spName) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    /**
     * 使用SharedPreferences判断是否存在
     *
     * @param spName 文件名
     * @param key    键
     */
    public static boolean existed(String spName, String key) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if ("".equals(sp.getString(key, ""))) {
            return false;
        } else {
            return true;
        }
    }
}

