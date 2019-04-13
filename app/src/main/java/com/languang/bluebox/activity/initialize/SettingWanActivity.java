package com.languang.bluebox.activity.initialize;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.LoadingPopView;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.facility.MobileActivity;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.entity.ResponseMessage;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;
import com.tencent.mmkv.MMKV;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 设置WAN
 *
 * @author 49829
 * @date 2018/4/10
 */
public class SettingWanActivity extends BaseFragmentActivity implements OkHttpCallBack {
    PopupWindow popupWindow;
    //    @BindView(R.id.down)
//    ImageView down;
//    @BindView(R.id.eye)
//    TextView display;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.display)
    LinearLayout display;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.wifi)
    MaterialSpinner wifi;
    @BindView(R.id.type1)
    MaterialSpinner type1;
    @BindView(R.id.ssss)
    LinearLayout linearLayout;
    //    PopListAdapter popListAdapter;
    LoadingPopView loadingPopView;
    List<String> wifis = new ArrayList<String>() {{
        add("");
    }};

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting_wan;
    }

    public int dipTopx(Context context, float dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void initView() {
        if (getIntent().getStringExtra("a") != null) {
            display.setVisibility(View.GONE);
            next.setText("保存");
        } else {
            next.setText("下一步");
        }
        loadingPopView = new LoadingPopView(this);
        TextView hostName = findViewById(R.id.hostName);
        TextView type = findViewById(R.id.type);
        TextView status = findViewById(R.id.status);
        TextView sn = findViewById(R.id.sn);
        TextView net = findViewById(R.id.net);
        NetPort netPort = new Gson().fromJson(MMKV.defaultMMKV()
                .decodeString("user"), NetPort.class);
        hostName.setText(netPort.getHostname());
        type.setText(netPort.getModel());
        status.setText(netPort.isActivate() == true ? "已激活" : "未激活");
        sn.setText(netPort.getSn());
        net.setText(netPort.getIp());
        final ArrayList list = new ArrayList();
        list.add("WIFI连接");
        list.add("DHCP连接");
        type1.setItems(list);
        type1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (position == 1) {
                    linearLayout.setVisibility(View.GONE);
                } else {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
//        popListAdapter = new PopListAdapter(this, list);
//        setTitle("设置WAN");
//        setRightText("保存");
//        setRightOnclick(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateWAN();
//            }
//        });
        View contentView = LayoutInflater.from(this)
                .inflate(R.layout.pop, null, false);
        RecyclerView popList = contentView.findViewById(R.id.popList);
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        popList.setLayoutManager(linearLayoutManager);
//        popList.setAdapter(popListAdapter);
//        popListAdapter.setMobileListen(new ImgOnClickListenner() {
//            @Override
//            public void onClick(int position) throws Exception {
//                display.setText((CharSequence) list.get(position));
//                popupWindow.dismiss();
//            }
//        });
        popupWindow = new PopupWindow(contentView, dipTopx(this, 300), LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_input_bg));
//        down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popupWindow.showAsDropDown(display);
//            }
//        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPopView.show();
                Map<String, Object> params = new HashMap<>(1);
                params.put("ssid", wifi.getText()
                        .toString());
                params.put("pwd", password.getText()
                        .toString());
                params.put("dhcp", type1.getText()
                        .toString()
                        .equals("DHCP连接") ? 1 : 0
                );
                params.put("token", MMKV.defaultMMKV()
                        .decodeString("token"));
                OkHttpUtils.getInstance()
                        .okPost(SettingWanActivity.this, TimeUtils.getGateway() + "/setbox", null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                Intent intent = new Intent(SettingWanActivity.this, MobileActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailed() {
                                loadingPopView.dissmiss();
                            }
                        });
            }
        });
    }

    public List<ScanResult> getWifiList() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        List<ScanResult> scanWifiList = wifiManager.getScanResults();
        List<ScanResult> wifiList = new ArrayList<>();
        if (scanWifiList != null && scanWifiList.size() > 0) {
            HashMap<String, Integer> signalStrength = new HashMap<String, Integer>();
            for (int i = 0; i < scanWifiList.size(); i++) {
                ScanResult scanResult = scanWifiList.get(i);
                if (!scanResult.SSID.isEmpty()) {
                    String key = scanResult.SSID + " " + scanResult.capabilities;
                    if (!signalStrength.containsKey(key)) {
                        signalStrength.put(key, i);
                        wifiList.add(scanResult);
                    }
                }
            }
        }
        return wifiList;
    }

    @Override
    protected void initData() {
        registerPermission();
    }

    private void registerPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    122);
        } else {
            for (ScanResult scanResult : getWifiList()) {
                wifis.add(scanResult.SSID);
            }
            wifi.setItems(wifis);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 122) {
            for (ScanResult scanResult : getWifiList()) {
                wifis.add(scanResult.SSID);
            }
            wifi.setItems(wifis);
        }
    }

    /**
     * 设置WAN
     */
    private void updateWAN() {
        Map<String, Object> params = new HashMap<>(4);
        params.put("protocol", "PPPoE");
        params.put("account", "abc");
        params.put("password", "123456");
        params.put("macAddress", "A4-56-03-78-87-B3");
        OkHttpUtils.getInstance()
                .okPost(mContext, ApiConstant.SETTING_WAN_URL, params, this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        ResponseMessage responseMessage = new Gson().fromJson(response, ResponseMessage.class);
        ToastUtilsBase.showToastCenter(mContext, responseMessage.getMsg());
        if (Constant.REQUEST_SUCCEED.equals(responseMessage.getRet())) {
            startActivity(new Intent(mContext, SettingPwdActivity.class));
            finish();
        } else {
//            ToastUtilsBase.showToastCenter(mContext,responseMessage.getMessage());
        }
    }

    @Override
    public void onFailed() {
    }
}
