package com.languang.bluebox.activity.facility;

import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrj.framworklib.utils.OkHttpCallBack;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.coustomview.CustomPercentView;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.facility.DiskInfo;
import com.languang.bluebox.presenter.IDiskManageInfo;
import com.languang.bluebox.model.DiskManageInfoModel;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 硬盘信息
 *
 * @author 49829
 * @date 2018/3/26
 */

public class DiskManageActivity extends BaseFragmentActivity implements OkHttpCallBack {

    @BindView(R.id.custom_percent_view)
    CustomPercentView customPercentView;
    @BindView(R.id.disk_name)
    TextView diskName;
    @BindView(R.id.disk_state)
    TextView diskState;
    @BindView(R.id.disk_reduce)
    TextView diskReduce;
    @BindView(R.id.disk_total)
    TextView diskTotal;

    private IDiskManageInfo model;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_disk_manage;
    }

    @Override
    protected void initView() {
        setTitle("硬盘信息");
        setRightText("保存");
        setRightOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void initData() {
        model = new DiskManageInfoModel(this);
        model.getDiskManageInfo(ApiConstant.GET_DISK_META_URL, this);
        dialogUtil.showDialog("加载中...");
    }

    @OnClick(R.id.clear_cache)
    public void onViewClicked() {
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        dialogUtil.dismissDialog();
//        if (ApiConstant.GET_DISK_META_URL.equals(requestUrl)) {
//            ResponseMessage<DiskInfo> responseMessage = new Gson().fromJson(response,
//                    new TypeToken<ResponseMessage<DiskInfo>>() {
//                    }.getType());
//            DiskInfo diskInfo = responseMessage.getData();
//            diskName.setText(diskInfo.getName());
//            diskState.setText("已使用" + (diskInfo.getStorage() - diskInfo.getUsed()) + "G/" + diskInfo.getStorage() + "G");
//            diskTotal.setText("总容量" + diskInfo.getStorage() + "G");
//            diskReduce.setText((diskInfo.getStorage() - diskInfo.getUsed()) + "G");
//
//            double[] doubles = new double[]{(double) diskInfo.getStatistics().get(0).getStorage()/diskInfo.getStorage()
//                    , (double)diskInfo.getStatistics().get(1).getStorage()/diskInfo.getStorage()
//                    , (double)diskInfo.getStatistics().get(2).getStorage()/diskInfo.getStorage()
//                    ,(double) diskInfo.getStatistics().get(3).getStorage()/diskInfo.getStorage()};
//
//            customPercentView.setPercent(doubles);
//
//
//        }
    }

    @Override
    public void onFailed() {
        dialogUtil.dismissDialog();
    }
}
