package com.languang.bluebox.entity.user;

import com.languang.bluebox.constant.Constant;

/**
 * 用户信息管理类
 *
 * @author 49829
 * @date 2018/4/12
 */

public class UserUtils {

    private static UserDto user;

    /**
     * 获取User对象
     *
     * @return 用户
     */
    public static UserDto getUser() {
        if (user == null) {
            user = new UserDto();
        }
        return user;
    }

    /**
     * 设置user对象
     *
     * @param userDto 用户信息
     */
    public static void setUser(UserDto userDto) {

    }

    /**
     * 判断盒子是否识别
     *
     * @return true 识别  false 未识别
     */
    public static boolean isMatch() {
        if (false) {
            return true;
        }
        return false;
    }

    /**
     * 判断用户身份
     *
     * @return true 管理员  false 游客
     */
    public static boolean isAdmin() {
        if (Constant.ADMIN_USER.equals(user.getRole())) {
            return true;
        }
        return false;
    }


}
