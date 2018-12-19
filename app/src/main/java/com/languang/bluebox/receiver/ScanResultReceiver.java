package com.languang.bluebox.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;


import java.util.List;

import com.languang.bluebox.entity.AccessPoint;
import com.languang.bluebox.utils.WiFiHandler;
import com.languang.bluebox.utils.wifi.Courier;
import com.languang.bluebox.utils.wifi.Global;

/**
 * 扫描结果监听
 *
 * @author Manco
 * @date 2016/10/9
 */

public class ScanResultReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("marj", intent.getAction());
        if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            boolean isScanned = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, true);
            if (isScanned) {
                List<AccessPoint> aps = WiFiHandler.instance().getScanAp();
                if (aps != null) {
                    Courier.send(Global.AP_AVAILABLE, WiFiHandler.mergeRelativeAPs(aps));
                }
            }
        }
    }
}
