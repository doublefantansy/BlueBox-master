package com.languang.bluebox.entity.login;


/**
 * 识别宝盒返回数据
 *
 * @author 49829
 * @date 2018/4/9
 */

public class LoginRes {


    /**
     * status : 9999
     * token : 8CB0D3FDAC2CDE908F3AFA094FC43042
     * type : pwd
     * msg : 成功登陆，等待跳转
     */

    private String status;
    private String token;
    private String type;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
