package com.languang.bluebox.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Message;

import com.languang.bluebox.utils.wifi.Courier;
import com.languang.bluebox.utils.wifi.Global;


/**
 *  wifi状态监听
 * @author mrj
 * @date 2016/10/9
 */
public class WiFiStateReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_DISABLED);
            Message message = Message.obtain();
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    message.what = Global.WiFiState.WIFI_DISABLED;
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    message.what = Global.WiFiState.WIFI_ENABLED;
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    message.what = Global.WiFiState.WIFI_ENABLEING;
                    break;
                default:
                    message.what = Global.UNKNOWN;
                    break;
            }
            Courier.send(message);
        }
    }
}
