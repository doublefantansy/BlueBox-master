package com.languang.bluebox.entity;

public class NetPort {
    /**
     * status : 9999
     * activate : true
     * hostname : 1234567
     * ip : 172.26.176.168
     * wlanip : 47.92.175.244
     * sn : boxhaotuweicom123
     * png : default/boxhaotuweicom123.png
     * model : X3456
     * date : 2018-09-04
     * time : 1544408683
     */
    private String status;
    private boolean activate;
    private String hostname;
    private String ip;
    private String wlanip;
    private String sn;
    private String png;
    private String model;
    private String date;
    private int time;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getWlanip() {
        return wlanip;
    }

    public void setWlanip(String wlanip) {
        this.wlanip = wlanip;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

