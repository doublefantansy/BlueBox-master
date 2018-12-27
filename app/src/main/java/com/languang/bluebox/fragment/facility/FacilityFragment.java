package com.languang.bluebox.fragment.facility;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.facility.FacilityListInfo;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设备页面
 *
 * @author 49829
 * @date 2018/3/26
 */
public class FacilityFragment extends BaseFragment {
    //    @BindView(R.id.wifi_name)
//    TextView wifiName;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    MyInfoDataAdapter adapter;
    List<FacilityListInfo> listInfos = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_facility;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkHttpUtils.getInstance()
                .okPost(getActivity(), ApiConstant.BLUES_LIST, null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        ResponseMessage1<List<FacilityListInfo>> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage1<List<FacilityListInfo>>>() {
                        }.getType());
                        if (responseMessage.getRet() == 200) {
                            listInfos.addAll(responseMessage.getData());
                            adapter.notifyDataSetChanged();
                            TimeUtils.isOnline(getActivity(), responseMessage.getData()
                                    .get(0)
                                    .getWlanip());
                        }
                    }

                    @Override
                    public void onFailed() {
                    }
                });
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyInfoDataAdapter(getActivity(), listInfos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
    }
//    @OnClick({R.id.my_wifi_rl
//            , R.id.facility_info_ll
//            , R.id.disk_manage_ll
//            , R.id.pwd_manage_ll
//            , R.id.visitor_pwd_ll
//            , R.id.restart})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.my_wifi_rl:
//                startActivity(new Intent(getActivity(), MyWifiActivity.class));
//                break;
//            case R.id.facility_info_ll:
//                startActivity(new Intent(getActivity(), FacilityInfoActivity.class));
//                break;
//            case R.id.disk_manage_ll:
//                startActivity(new Intent(getActivity(), DiskManageActivity.class));
//                break;
//            case R.id.pwd_manage_ll:
//                startActivity(new Intent(getActivity(), AdminPwdActivity.class));
//                break;
//            case R.id.visitor_pwd_ll:
//                startActivity(new Intent(getActivity(), VisitorPwdActivity.class));
//                break;
//            case R.id.restart:
//                OkHttpUtils.getInstance().okGet(getActivity(), "http://result.eolinker.com/vaYZizz7dd1c49fc140f8522e29f5418e4293e5ff137f38?uri=/CDMeta", new OkHttpCallBack() {
//                    @Override
//                    public void onSucceed(String requestUrl, String response) {
//                        Log.d(TAG, "onSucceed: " + response);
//                    }
//
//                    @Override
//                    public void onFailed() {
//
//                    }
//                });
//                break;
//            default:
//                break;
//        }
//    }
}
