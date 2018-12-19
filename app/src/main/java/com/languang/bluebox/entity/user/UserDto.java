package com.languang.bluebox.entity.user;

/**
 * 用户信息
 *
 * @author 49829
 * @date 2018/4/12
 */

public class UserDto {
    /**
     * 用户角色信息 admin 管理员  guest 游客
     */
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
