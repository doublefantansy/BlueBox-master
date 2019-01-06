package com.languang.bluebox.activity.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.ErrorActivity;
import com.languang.bluebox.MainActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.adapter.FacilityListAdapter;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
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

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;
    private IFacilityList facilityListModel;
    private FacilityListAdapter adapter;
    private List<FacilityListInfo> listInfos = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_facility_list;
    }

    @Override
    protected void initView() {
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
                String token = SharedPrefsUtil.getValue("InitialInfo", "AccessToken", "");
                Log.d("ccnb", token);
                facilityListModel.boxLogin(token, MobileInfoUtils.getIMEI(FacilityListActivity.this),
                        listInfos.get(position)
                                .getPwdmd5(), listInfos.get(position)
                                .getMobile(),
                        FacilityListActivity.this);
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
                    adapter.clear();
                    adapter.addAll(listInfos);
                    adapter.notifyDataSetChanged();
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
                    ToastUtilsBase.showToastCenter(this, refreshInfoResponseMessage.getData()
                            .getMsg());
                }
            } else if (ApiConstant.BOX_LOGIN.equals(requestUrl)) {
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
