package com.languang.bluebox.activity.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.ErrorActivity;
import com.languang.bluebox.MainActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.adapter.FacilityListAdapter;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SharedPrefsUtil;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.RefreshInfo;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.facility.FacilityListInfo;
import com.languang.bluebox.model.FacilityListModel;
import com.languang.bluebox.presenter.IFacilityList;
import com.languang.bluebox.utils.MobileInfoUtils;
import com.languang.bluebox.utils.ScreenUtils;
import com.luck.easyrecyclerview.EasyRecyclerView;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mrj.framworklib.utils.AppManager;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;
import com.xuexiang.xqrcode.ui.CaptureActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备列表页面
 *
 * @author mrj
 * 2018/11/16 10:06
 */
public class FacilityListActivity extends BaseFragmentActivity implements OkHttpCallBack {
    @BindView(R.id.online)
    TextView online;
    @BindView(R.id.offline)
    TextView offline;
    @BindView(R.id.tv_new)
    TextView tonew;
    @BindView(R.id.ssssss)
    TitleBar bar;
    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;
    private IFacilityList facilityListModel;
    private FacilityListAdapter adapter;
    private List<FacilityListInfo> listInfos = new ArrayList<>();
    private WindowManager.LayoutParams ll;
    PopupWindow popupWindow;
    int p;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_facility_list;
    }

    public static int dipTopx(Context context, float dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("cccc", requestCode + "|" + resultCode + "|" + data + "");
    }

    @Override
    protected void initView() {
        bar.addAction(new TitleBar.Action() {
            @Override
            public String getText() {
                return null;
            }

            @Override
            public int getDrawable() {
                return R.drawable.ic_jia_2;
            }

            @Override
            public void performAction(View view) {
                startActivityForResult(new Intent(FacilityListActivity.this, CaptureActivity.class), 222);
            }

            @Override
            public int leftPadding() {
                return 0;
            }

            @Override
            public int rightPadding() {
                return 0;
            }
        });
        ll = getWindow()
                .getAttributes();
        popupWindow = new PopupWindow();
        popupWindow.setWidth(dipTopx(this, 300));
        popupWindow.setHeight(dipTopx(this, 200));
        popupWindow.setFocusable(true);
        View view1 = getLayoutInflater().inflate(R.layout.rename1, null);
        Button cancel = view1.findViewById(R.id.cancel);
        Button yes = view1.findViewById(R.id.yes);
        final EditText sn = view1.findViewById(R.id.sn);
        final EditText password = view1.findViewById(R.id.password);
        popupWindow.setContentView(view1);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.alpha = 1f;
                getWindow()
                        .setAttributes(ll);
                popupWindow.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("blueuuid", sn.getText()
                        .toString());
                map.put("pwd", password.getText()
                        .toString());
                OkHttpUtils.getInstance()
                        .okPost(FacilityListActivity.this, ApiConstant.CLOUD_BASE_URL + "/blueinsert", map, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                Log.d("ccnb", response);
                                ll.alpha = 1f;
                                getWindow()
                                        .setAttributes(ll);
                                popupWindow.dismiss();
                                sn.setText("");
                                password.setText("");
                                OkHttpUtils.getInstance()
                                        .okPost(FacilityListActivity.this, ApiConstant.CLOUD_BLUES, null, new OkHttpCallBack() {
                                            @Override
                                            public void onSucceed(String requestUrl, String response) {
                                                Log.d("ccnbccnb", response);
                                                try {
                                                    if (ApiConstant.CLOUD_BLUES.equals(requestUrl)) {
                                                        ResponseMessage<List<FacilityListInfo>> listResponseMessage = new Gson().fromJson(response,
                                                                new TypeToken<ResponseMessage<List<FacilityListInfo>>>() {
                                                                }.getType());
                                                        if (Constant.SUCCEED_CODE == listResponseMessage.getRet()) {
                                                            listInfos = listResponseMessage.getData();
                                                            for (FacilityListInfo listInfo : listInfos) {
                                                                if (!TimeUtils.getGateway()
                                                                        .equals("http://box.haotuwei.com")) {
                                                                    listInfo.setOnline(TimeUtils.isOnline(FacilityListActivity.this, listInfo.getLanip()));
                                                                } else {
                                                                    listInfo.setOnline(true);
                                                                }
//                                                                Log.d("cctag", TimeUtils.isOnline(FacilityListActivity.this, listInfo.getWlanip()) + "");
                                                            }
                                                            adapter.clear();
                                                            adapter.addAll(listInfos);
                                                            adapter.notifyDataSetChanged();
                                                            if (listInfos.size() == 1) {
                                                                if (listInfos.get(0)
                                                                        .isOnline()) {
                                                                    String token = SharedPrefsUtil.getValue("InitialInfo", "AccessToken", "");
                                                                    if (!TimeUtils.getGateway()
                                                                            .equals("http://box.haotuwei.com")) {
                                                                        TimeUtils.setWlanIp(true, "http://" + listInfos.get(0)
                                                                                .getLanip());
                                                                    }
                                                                    facilityListModel.boxLogin(token, MobileInfoUtils.getIMEI(FacilityListActivity.this),
                                                                            listInfos.get(0)
                                                                                    .getPwdmd5(), listInfos.get(0)
                                                                                    .getMobile(),
                                                                            FacilityListActivity.this);
                                                                }
                                                            }
                                                        }
                                                    } else if (ApiConstant.CLOUD_BLUE_INSERT.equals(requestUrl)) {
                                                    } else if (ApiConstant.CLOUD_BLUE_DEL.equals(requestUrl)) {
                                                    } else if (ApiConstant.CLOUD_BLUE_SET.equals(requestUrl)) {
                                                        ResponseMessage responseMessage = new Gson().fromJson(response, ResponseMessage.class);
                                                        if (Constant.SUCCEED_CODE == responseMessage.getRet()) {
                                                            facilityListModel.getBoxList(this);
                                                        }
                                                    } else if (ApiConstant.CLOUD_REFRESH.equals(requestUrl)) {
                                                        ResponseMessage<RefreshInfo> refreshInfoResponseMessage = new Gson().fromJson(response,
                                                                new TypeToken<ResponseMessage<RefreshInfo>>() {
                                                                }.getType());
                                                        if (Constant.SUCCEED_CODE == refreshInfoResponseMessage.getRet()) {
                                                            ToastUtilsBase.showToastCenter(FacilityListActivity.this, refreshInfoResponseMessage.getData()
                                                                    .getMsg());
                                                        }
                                                    } else if ((TimeUtils.getWlanIp() + "/boxlogin").equals(requestUrl)) {
                                                        ResponseMessage<LoginBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<LoginBean>>() {
                                                        }.getType());
                                                        if (Constant.SUCCEED_CODE == responseMessage.getRet()) {
                                                            if (responseMessage.getData()
                                                                    .getStatus()
                                                                    .equals("9999")) {
                                                                startActivity(new Intent(FacilityListActivity.this, MainActivity.class));
                                                            } else {
                                                                Toast.makeText(FacilityListActivity.this, responseMessage.getData()
                                                                        .getMsg(), Toast.LENGTH_SHORT)
                                                                        .show();
                                                            }
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    Intent intent = new Intent(FacilityListActivity.this, ErrorActivity.class);
                                                    intent.putExtra("req", requestUrl);
                                                    intent.putExtra("res", response);
                                                    startActivity(intent);
                                                }
                                            }

                                            @Override
                                            public void onFailed() {
                                            }
                                        });
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
            }
        });
        tonew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.alpha = 0.5f;
                getWindow()
                        .setAttributes(ll);
                popupWindow.showAtLocation(getWindow()
                        .getDecorView(), Gravity.CENTER, 0, 0);
            }
        });
        online.setSelected(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(manager);
        adapter = new FacilityListAdapter(this);
        adapter.addAll(listInfos);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (listInfos.get(position)
                        .isOnline()) {
                    String token = SharedPrefsUtil.getValue("InitialInfo", "AccessToken", "");
                    if (!TimeUtils.getGateway()
                            .equals("http://box.haotuwei.com")) {
                        TimeUtils.setWlanIp(true, "http://" + listInfos.get(position)
                                .getLanip());
                    }
                    facilityListModel.boxLogin(token, MobileInfoUtils.getIMEI(FacilityListActivity.this),
                            listInfos.get(position)
                                    .getPwdmd5(), listInfos.get(position)
                                    .getMobile(),
                            FacilityListActivity.this);
                    p = position;
                }
            }
        });
    }

    @Override
    protected void initData() {
        facilityListModel = new FacilityListModel(this);
        facilityListModel.getBoxList(this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        Log.d("ccnbccnb", response);
        try {
            if (ApiConstant.CLOUD_BLUES.equals(requestUrl)) {
                ResponseMessage<List<FacilityListInfo>> listResponseMessage = new Gson().fromJson(response,
                        new TypeToken<ResponseMessage<List<FacilityListInfo>>>() {
                        }.getType());
                if (Constant.SUCCEED_CODE == listResponseMessage.getRet()) {
                    listInfos = listResponseMessage.getData();
//                    Log.d("ccnbccnb",listInfos+"nmsl");
                    for (FacilityListInfo listInfo : listInfos) {
                        if (!TimeUtils.getGateway()
                                .equals("http://box.haotuwei.com")) {
                            listInfo.setOnline(TimeUtils.isOnline(FacilityListActivity.this, listInfo.getLanip()));
                        } else {
                            listInfo.setOnline(true);
                        }
//                        listInfo.setOnline(TimeUtils.isOnline(this, listInfo.getLanip()));
//                        Log.d("cctag", TimeUtils.isOnline(this, listInfo.getWlanip()) + "");
                    }
                    adapter.clear();
                    adapter.addAll(listInfos);
                    adapter.notifyDataSetChanged();
                    if (listInfos.size() == 1) {
                        if (listInfos.get(0)
                                .isOnline()) {
                            String token = SharedPrefsUtil.getValue("InitialInfo", "AccessToken", "");
                            if (!TimeUtils.getGateway()
                                    .equals("http://box.haotuwei.com")) {
                                TimeUtils.setWlanIp(true, "http://" + listInfos.get(0)
                                        .getLanip());
                            }
                            facilityListModel.boxLogin(token, MobileInfoUtils.getIMEI(FacilityListActivity.this),
                                    listInfos.get(0)
                                            .getPwdmd5(), listInfos.get(0)
                                            .getMobile(),
                                    FacilityListActivity.this);
                        }
                    }
                }
            } else if (ApiConstant.CLOUD_BLUE_SET.equals(requestUrl)) {
                ResponseMessage responseMessage = new Gson().fromJson(response, ResponseMessage.class);
                if (Constant.SUCCEED_CODE == responseMessage.getRet()) {
                    facilityListModel.getBoxList(this);
                }
            } else if (ApiConstant.CLOUD_REFRESH.equals(requestUrl)) {
                ResponseMessage<RefreshInfo> refreshInfoResponseMessage = new Gson().fromJson(response,
                        new TypeToken<ResponseMessage<RefreshInfo>>() {
                        }.getType());
                if (Constant.SUCCEED_CODE == refreshInfoResponseMessage.getRet()) {
                    ToastUtilsBase.showToastCenter(this, refreshInfoResponseMessage.getData()
                            .getMsg());
                }
            } else if ((TimeUtils.getWlanIp() + "/boxlogin").equals(requestUrl)) {
                ResponseMessage<LoginBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<LoginBean>>() {
                }.getType());
                if (Constant.SUCCEED_CODE == responseMessage.getRet()) {
                    if (responseMessage.getData()
                            .getStatus()
                            .equals("9999")) {
                        Intent intent = new Intent(FacilityListActivity.this, MainActivity.class);
                        intent.putExtra("p", p);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(FacilityListActivity.this, responseMessage.getData()
                                .getMsg(), Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }
        } catch (Exception e) {
            Log.d("ccnbccnb", e.getMessage());
            Log.d("ccnbccnb", response);
        }
    }

    @Override
    public void onFailed() {
    }

    @OnClick({R.id.iv_refresh, R.id.online, R.id.offline, R.id.tv_new})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_refresh:
                facilityListModel.refreshList(this);
                break;
            case R.id.online:
                online.setSelected(true);
                offline.setSelected(false);
                break;
            case R.id.offline:
                offline.setSelected(true);
                online.setSelected(false);
                break;
            case R.id.tv_new:
                break;
        }
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > Constant.EXIST_TIME) {
            ToastUtilsBase.showToastBottom(this, "再次按返回键退出");
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
            AppManager.getAppManager()
                    .finishAllActivity();
        }
    }

    /**
     * @param uid
     */
    public void showDialog(final String uid) {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.dialog_change_nick, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view)
                .create();
        TextView btn_cancel_high_opion = view.findViewById(R.id.cancel);
        TextView btn_agree_high_opion = view.findViewById(R.id.sure);
        final EditText nickEt = view.findViewById(R.id.nick_et);
        btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //... To-do
                dialog.dismiss();
            }
        });
        btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //... To-do
                String nick = nickEt.getText()
                        .toString();
                if (TextUtils.isEmpty(nick)) {
                    ToastUtilsBase.showToastCenter(FacilityListActivity.this, "昵称不能为空");
                    return;
                }
                facilityListModel.setBoxNick(uid, nick, FacilityListActivity.this);
                dialog.dismiss();
            }
        });
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4
        dialog.getWindow()
                .setLayout((ScreenUtils.getScreenWidth(this) / 4 * 3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void deleteBox(String uid) {
        facilityListModel.deleteBox(uid, this);
    }
}
