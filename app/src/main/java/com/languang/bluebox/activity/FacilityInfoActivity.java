package com.languang.bluebox.activity;

import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.ArrayList;
import java.util.List;

import com.languang.bluebox.R;
import com.languang.bluebox.adapter.FacilityInfoAdapter;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.facility.FacilityInfo;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.presenter.IFacilityInfo;
import com.languang.bluebox.model.FacilityInfoModel;
import butterknife.BindView;

/**
 * 设备信息页面
 *
 * @author 49829
 * @date 2018/3/26
 */

public class FacilityInfoActivity extends BaseFragmentActivity implements OkHttpCallBack {

    @BindView(R.id.list_view)
    ListView listView;

    private IFacilityInfo model;
    private FacilityInfoAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_facility_info;
    }

    @Override
    protected void initView() {
        setTitle("设备信息");

    }

    @Override
    protected void initData() {
        model = new FacilityInfoModel(this);
        model.getFacilityInfo(ApiConstant.GET_BOX_META_URL, this);
        dialogUtil.showDialog("加载中...");
    }


    @Override
    public void onSucceed(String requestUrl, String response) {
        dialogUtil.dismissDialog();
        if (ApiConstant.GET_BOX_META_URL.equals(requestUrl)) {
            ResponseMessage<FacilityInfo> responseMessage = new Gson().fromJson(response,
                    new TypeToken<ResponseMessage<FacilityInfo>>() {
                    }.getType());

            list.add("蓝光宝盒001");
            list.add("CXS3445DZDA0032");
            list.add("115.171.197.19");
            list.add("CXS3445DZDA0032");
            list.add("20180904-001A");

            adapter = new FacilityInfoAdapter(this, list);

            listView.setAdapter(adapter);

        }
    }

    @Override
    public void onFailed() {
        dialogUtil.dismissDialog();
    }
}
