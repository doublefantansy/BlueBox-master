package com.languang.bluebox.activity.property;

import android.os.Bundle;
import android.view.View;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.model.PropertySheetModel;
import com.languang.bluebox.presenter.IPropertySheet;
import com.mrj.framworklib.utils.OkHttpCallBack;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 导出设备
 *
 * @author 49829
 * @date 2018/4/17
 */

public class ExportFacilityActivity extends BaseFragmentActivity implements OkHttpCallBack {


    private IPropertySheet propertySheet;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_export_facility;
    }

    @Override
    protected void initView() {
        setTitle("导出设备");
    }

    @Override
    protected void initData() {
        propertySheet = new PropertySheetModel(this);
        propertySheet.facilityList("usb",this);
    }


    @OnClick({R.id.browse_1, R.id.browse_2})
    public void onViewClicked(View view) {
        String[] files = new String[2];
        switch (view.getId()) {
            case R.id.browse_1:
                propertySheet.startOut(files, this);
                break;
            case R.id.browse_2:
                propertySheet.startOut(files, this);
                break;
        }
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        if (ApiConstant.BOX_WAISHE_INFO.equals(requestUrl)){

        }
    }

    @Override
    public void onFailed() {

    }
}
