package com.languang.bluebox.model;

import android.content.Context;

import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.presenter.IPropertySheet;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.HashMap;
import java.util.Map;

public class PropertySheetModel implements IPropertySheet {

    private Context context;

    public PropertySheetModel(Context context) {
        this.context = context;
    }

    @Override
    public void getOutList(OkHttpCallBack callBack) {
        OkHttpUtils.getInstance().okPost(context, ApiConstant.BOX_LIST_OUT, null, callBack);
    }

    @Override
    public void deleteOutFile(String[] files, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("files", files);
        OkHttpUtils.getInstance().okPost(context, ApiConstant.BOX_DEL_OUT, params, callBack);
    }

    @Override
    public void startOut( String[] files, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("outfile", "all");
        params.put("files", files);
        OkHttpUtils.getInstance().okPost(context, ApiConstant.BOX_START_OUT, params, callBack);
    }

    @Override
    public void facilityList(String type, OkHttpCallBack callBack) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("type", type);
        OkHttpUtils.getInstance().okPost(context, ApiConstant.BOX_WAISHE_INFO, params, callBack);
    }
}
