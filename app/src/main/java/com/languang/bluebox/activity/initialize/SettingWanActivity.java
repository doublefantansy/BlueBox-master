package com.languang.bluebox.activity.initialize;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.activity.facility.AdminPwdActivity;
import com.languang.bluebox.activity.facility.MobileActivity;
import com.languang.bluebox.adapter.ImgOnClickListenner;
import com.languang.bluebox.adapter.PopListAdapter;
import com.languang.bluebox.entity.NetPort;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.ResponseMessage;
import com.tencent.mmkv.MMKV;

import butterknife.BindView;

/**
 * 设置WAN
 *
 * @author 49829
 * @date 2018/4/10
 */
public class SettingWanActivity extends BaseFragmentActivity implements OkHttpCallBack {
    PopupWindow popupWindow;
    @BindView(R.id.down)
    ImageView down;
    @BindView(R.id.eye)
    TextView display;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.wifi)
    TextView wifi;
    PopListAdapter popListAdapter;

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
        list.add("DHCP连接");
        list.add("WIFI连接");
        popListAdapter = new PopListAdapter(this, list);
        setTitle("设置WAN");
//        setRightText("保存");
        setRightOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateWAN();
            }
        });
        View contentView = LayoutInflater.from(this)
                .inflate(R.layout.pop, null, false);
        RecyclerView popList = contentView.findViewById(R.id.popList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        popList.setLayoutManager(linearLayoutManager);
        popList.setAdapter(popListAdapter);
        popListAdapter.setMobileListen(new ImgOnClickListenner() {
            @Override
            public void onClick(int position) throws Exception {
                display.setText((CharSequence) list.get(position));
                popupWindow.dismiss();
            }
        });
//         final TextView s = contentView.findViewById(R.id.s);
//         RelativeLayout sa = contentView.findViewById(R.id.sa);
//        sa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                display.setText(s.getText());
//                s.setText(display.getText());
//                popupWindow.dismiss();
//            }
//        });
        popupWindow = new PopupWindow(contentView, dipTopx(this, 300), LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_input_bg));
//        popupWindow = new PopupWindow();
//        popupWindow.
//        popupWindow.setContentView(getLayoutInflater().inflate(, null));
//        popupWindow.setlis
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAsDropDown(display);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>(1);
                params.put("ssid", wifi.getText()
                        .toString());
                params.put("pwd", password.getText()
                        .toString());
                params.put("dhcp", display.getText()
                        .toString()
                        .equals("DHCP连接") ? 1 : 0
                );
                params.put("token", MMKV.defaultMMKV()
                        .decodeString("token"));
                OkHttpUtils.getInstance()
                        .okPost(SettingWanActivity.this, ApiConstant.WLAN_SET_BOX, null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                Intent intent = new Intent(SettingWanActivity.this, MobileActivity.class);
//                                intent.putExtra("pwd", password1.getText()
//                                        .toString());
                                startActivity(intent);
                                Log.d("ccnb", "succ");
                            }

                            @Override
                            public void onFailed() {
                                Log.d("ccnb", "fail");
                            }
                        });
            }
        });
    }

    @Override
    protected void initData() {
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
