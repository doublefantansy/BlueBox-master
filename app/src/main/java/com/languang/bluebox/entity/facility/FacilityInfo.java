package com.languang.bluebox.entity.facility;

import java.util.ArrayList;
import java.util.List;

/**
 *蓝光宝盒设备信息
 * @author 49829
 * @date 2018/4/8
 */

public class FacilityInfo {

    /**
     * name :
     * serialNumber :
     * WANIP :
     * CDBurnerSerialNumber :
     * ROMVersion :
     */
    private String name;
    private String serialNumber;
    private String WANIP;
    private String CDBurnerSerialNumber;
    private String ROMVersion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getWANIP() {
        return WANIP;
    }

    public void setWANIP(String WANIP) {
        this.WANIP = WANIP;
    }

    public String getCDBurnerSerialNumber() {
        return CDBurnerSerialNumber;
    }

    public void setCDBurnerSerialNumber(String CDBurnerSerialNumber) {
        this.CDBurnerSerialNumber = CDBurnerSerialNumber;
    }

    public String getROMVersion() {
        return ROMVersion;
    }

    public void setROMVersion(String ROMVersion) {
        this.ROMVersion = ROMVersion;
    }

}
