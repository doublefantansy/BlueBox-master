package com.languang.bluebox;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

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
        return "http://" + ip;
//        return "http://box.haotuwei.com";
//        ApiConstant.BOX_BASE_URL
    }

    public static boolean isOnline(Context context, String wlanIp) {
        Log.d("ccnbccnbccnb", intToIp1(mask(context)));
        String[] ips = getIp(context).split("\\.");
        String[] wlans = wlanIp.split("\\.");
        String[] masks = intToIp1(mask(context)).split("\\.");
        long[] longIps = new long[4];
        long[] longWlans = new long[4];
        long[] longMasks = new long[4];
        boolean[] result = new boolean[4];
        for (int i = 0; i < ips.length; i++) {
            longMasks[i] = (Long.valueOf(masks[i]));
        }
        for (int i = 0; i < ips.length; i++) {
            longIps[i] = (Long.valueOf(ips[i]));
        }
        for (int i = 0; i < wlans.length; i++) {
            longWlans[i] = (Long.valueOf(wlans[i]));
        }
        for (int i = 0; i < 4; i++) {
            long temp1 = longMasks[i] & longIps[i];
            long temp2 = longMasks[i] & longWlans[i];
            Log.d("ccnbccnbccnbccnb", temp1 + "");
            Log.d("ccnbccnbccnbccnb", temp2 + "");
            if (temp1 == temp2) {
                result[i] = true;
            } else {
                result[i] = false;
            }
        }
        for (boolean b : result) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public static int mask(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo info = wifiManager.getDhcpInfo();
//        OkHttpUtils
        return info.netmask;
//        return "http://box.haotuwei.com";
    }

    public static String getWlanIp() {
        return wlanIp;
//        return "http://box.haotuwei.com";
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
