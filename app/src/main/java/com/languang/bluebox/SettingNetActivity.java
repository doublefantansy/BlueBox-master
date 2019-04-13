package com.languang.bluebox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.activity.initialize.SettingPwdActivity;
import com.languang.bluebox.adapter.PopListAdapter;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.entity.ResponseMessage;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class SettingNetActivity extends BaseFragmentActivity implements OkHttpCallBack {
    PopupWindow popupWindow;
//    @BindView(R.id.down)
//    ImageView down;
//    @BindView(R.id.eye)
//    TextView display;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.wifi)
    TextView wifi;
    PopListAdapter popListAdapter;
    LoadingPopView popView;

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
        popView = new LoadingPopView(this);
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
//        setRightOnclick(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateWAN();
//            }
//        });
        View contentView = LayoutInflater.from(this)
                .inflate(R.layout.pop, null, false);
        RecyclerView popList = contentView.findViewById(R.id.popList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        popList.setLayoutManager(linearLayoutManager);
        popList.setAdapter(popListAdapter);
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
                popView.show();
                Map<String, Object> params = new HashMap<>(1);
                params.put("ssid", wifi.getText()
                        .toString());
                params.put("pwd", password.getText()
                        .toString());
//                params.put("dhcp", display.getText()
//                        .toString()
//                        .equals("DHCP连接") ? 1 : 0
//                );
                params.put("token", MMKV.defaultMMKV()
                        .decodeString("token"));
                OkHttpUtils.getInstance()
                        .okPost(SettingNetActivity.this, TimeUtils.getGateway() + "/setbox", null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                ResponseMessage<BoxBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<BoxBean>>() {
                                }.getType());
                                if (responseMessage.getData()
                                        .getStatus()
                                        .equals("9999")) {
                                    finish();
                                }
                            }

                            @Override
                            public void onFailed() {
                                Log.d("ccnb", "fail");
                                popView.dissmiss();
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
