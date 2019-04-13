package com.languang.bluebox;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.DhcpInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class TimeUtils {
    public static String wlanIp;
    public static String gate;

    public TimeUtils() {
    }

    public static String getIp(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo info = wifiManager.getDhcpInfo();
        return intToIp1(info.ipAddress);
    }

    public static String getGateway() {
        return gate;
    }

    public static void setGateway(Context context, boolean a) {
        if (a) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            DhcpInfo info = wifiManager.getDhcpInfo();
            String ip = intToIp1(info.gateway);
            gate = "http://" + ip;
        } else {
            gate = "http://box.haotuwei.com";
        }
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
//        return wlanIp;
        return wlanIp;
    }

    public static Uri saveBitmap(Bitmap bm, String picName) {
        try {
            String dir = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/renji/" + picName + ".jpg";
            File f = new File(dir);
            if (!f.exists()) {
                f.getParentFile()
                        .mkdirs();
                f.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Uri uri = Uri.fromFile(f);
            return uri;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setWlanIp(boolean a, String wlanIp) {
        if (a) {
            TimeUtils.wlanIp = wlanIp;
        } else {
            TimeUtils.wlanIp = "http://box.haotuwei.com";
        }
    }

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

    public static String saveBitmap(Context context, Bitmap mBitmap) {
        final String SD_PATH = "/sdcard/dskqxt/pic/";
        final String IN_PATH = "/dskqxt/pic/";
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState()
                .equals(
                        Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext()
                    .getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(savePath + UUID.randomUUID()
                    .toString() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile()
                        .mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return filePic.getAbsolutePath();
    }
}
