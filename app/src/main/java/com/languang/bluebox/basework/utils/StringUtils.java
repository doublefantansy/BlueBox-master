package com.languang.bluebox.basework.utils;

import android.support.annotation.Nullable;

import java.text.DecimalFormat;

/**
 * 字符串工具类
 *
 * @author mrj
 * @date 2017/8/7
 */

public class StringUtils {
    /**
     * 判断字符串是否位空
     *
     * @param str 字符串
     * @return 是 否
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    /**
     * double转String,保留小数点后两位
     *
     * @param num
     * @return 字符串
     */
    public static String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
