package com.languang.bluebox.activity.login;

class LoginBean {
    /**
     * status : 9999
     * token : D4B773E57AB6AF82612343D55C05F3E2
     * power : admin
     * IMEI : 867855027950624
     * msg : 登陆写入成功
     */
    private String status;
    private String token;
    private String power;
    private String IMEI;
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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
