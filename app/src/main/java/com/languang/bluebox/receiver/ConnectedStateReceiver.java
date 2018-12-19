package com.languang.bluebox.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.languang.bluebox.utils.wifi.Courier;
import com.languang.bluebox.utils.wifi.Global;


/**
 * 连接状态监听
 *
 * @author marj
 * @date 2016/10/9
 */
public class ConnectedStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(
                WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {
            /**
             * when some certain ap connects fail because of wrong password,here comes a broadcast
             */
            int error = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, 0);
            if (WifiManager.ERROR_AUTHENTICATING == error) {
                Courier.send(Global.ConnectState.AUTHENTICATE_FAILURE);
            }
        }

        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != info) {
                NetworkInfo.DetailedState state = info.getDetailedState();
                Log.d("marj", "connect state: " + state);
                if (info.isConnectedOrConnecting() && info.isConnected()) {
                    if (0 == state.compareTo(NetworkInfo.DetailedState.CONNECTED)) {
                        WifiInfo wifiInfo = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO);
                        if (null != wifiInfo) {
                            Courier.send(Global.ConnectState.CONNECT_SUCCESS);
//                            if (TextUtils.isEmpty(wifiInfo.getBSSID()) && TextUtils.isEmpty(wifiInfo.getSSID())) {
//                                Courier.send(Global.ConnectState.CONNECT_SUCCESS);
//                            }
                        }
                    }
                }
            }
        }
    }
}
