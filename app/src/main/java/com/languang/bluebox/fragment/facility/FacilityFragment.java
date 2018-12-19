package com.languang.bluebox.fragment.facility;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mrj.framworklib.utils.OkHttpCallBack;

import com.languang.bluebox.R;
import com.languang.bluebox.activity.FacilityInfoActivity;
import com.languang.bluebox.activity.facility.DiskManageActivity;
import com.languang.bluebox.activity.facility.AdminPwdActivity;
import com.languang.bluebox.activity.facility.MyWifiActivity;
import com.languang.bluebox.activity.facility.VisitorPwdActivity;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备页面
 *
 * @author 49829
 * @date 2018/3/26
 */

public class FacilityFragment extends BaseFragment {
    @BindView(R.id.wifi_name)
    TextView wifiName;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_facility;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.my_wifi_rl
            , R.id.facility_info_ll
            , R.id.disk_manage_ll
            , R.id.pwd_manage_ll
            , R.id.visitor_pwd_ll
            , R.id.restart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_wifi_rl:
                startActivity(new Intent(getActivity(), MyWifiActivity.class));
                break;
            case R.id.facility_info_ll:
                startActivity(new Intent(getActivity(), FacilityInfoActivity.class));
                break;
            case R.id.disk_manage_ll:
                startActivity(new Intent(getActivity(), DiskManageActivity.class));
                break;
            case R.id.pwd_manage_ll:
                startActivity(new Intent(getActivity(), AdminPwdActivity.class));
                break;
            case R.id.visitor_pwd_ll:
                startActivity(new Intent(getActivity(), VisitorPwdActivity.class));
                break;
            case R.id.restart:
                OkHttpUtils.getInstance().okGet(getActivity(), "http://result.eolinker.com/vaYZizz7dd1c49fc140f8522e29f5418e4293e5ff137f38?uri=/CDMeta", new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        Log.d(TAG, "onSucceed: " + response);
                    }

                    @Override
                    public void onFailed() {

                    }
                });
                break;
            default:
                break;
        }
    }
}
