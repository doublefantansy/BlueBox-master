package com.languang.bluebox.entity;

public class RefreshInfo {

    /**
     * status : 9999
     * count : 1
     * mobile : 15801653609
     * msg : 唤醒请求发出
     */

    private String status;
    private int count;
    private String mobile;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
