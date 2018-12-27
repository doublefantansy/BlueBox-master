package com.languang.bluebox;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;

import java.text.SimpleDateFormat;

public class TimeUtils {
    public static String wlanIp;

    public TimeUtils() {
    }

    public static String getIp(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo info = wifiManager.getDhcpInfo();
        return intToIp1(info.ipAddress);
//        return "https://" + ip;
//         ip;
    }

    public static String getGateway(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo info = wifiManager.getDhcpInfo();
        String ip = intToIp1(info.gateway);
//        return "https://" + ip;
        return "http://box.haotuwei.com";
    }

    public static boolean isOnline(Context context, String wlanIp) {
        String[] wlanIps = wlanIp.split("\\.");
        if (1 == 1) {
            return true;
        }
        return false;
    }

    public static int mask(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo info = wifiManager.getDhcpInfo();
        return info.netmask;
//        return "http://box.haotuwei.com";
    }

    public static String getWlanIp() {
//        return wlanIp;
        return "http://box.haotuwei.com";
    }

    public static void setWlanIp(String wlanIp) {
        TimeUtils.wlanIp = wlanIp;
    }

    //    public static String intToIp1(int addr) {
//        return ((addr & 0xFF) + "." +
//                ((addr >>>= 8) & 0xFF) + "." +
//                ((addr >>>= 8) & 0xFF) + "." +
//                ((addr >>>= 8) & 0xFF));
//    }
    private static String intToIp1(int paramInt) {
        return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "."
                + (0xFF & paramInt >> 24);
    }

    public static String long2String(long time) {
        int sec = (int) time / 1000;
        int min = sec / 60;
        sec %= 60;
        return min < 10 ? (sec < 10 ? "0" + min + ":0" + sec : "0" + min + ":" + sec) : (sec < 10 ? min + ":0" + sec : min + ":" + sec);
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(Long.valueOf(System.currentTimeMillis()));
    }
}
