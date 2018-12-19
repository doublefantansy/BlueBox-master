package com.languang.bluebox.fragment.mapstorage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.languang.bluebox.utils.ImgUtil;
import com.luck.easyrecyclerview.EasyRecyclerView;
import com.luck.easyrecyclerview.decoration.RecycleViewDivider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.languang.bluebox.R;
import com.languang.bluebox.adapter.picturestorege.PictureTimeAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.utils.SoftKeyBoardListener;
import com.languang.bluebox.coustomview.CustomEditText;
import com.languang.bluebox.coustomview.tabview.Display;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 时间分类图片
 *
 * @author 49829
 * @date 2018/3/29
 */

public class PictureTimeFragment extends BaseFragment implements OkHttpCallBack {

    @BindView(R.id.search_et)
    CustomEditText searchEt;
    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;


    private List<String> stringList = new ArrayList<>();
    private PictureTimeAdapter adapter;
    private List<ImgListEntity> imgEntities = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_time;
    }

    @Override
    protected void initView(View view) {
        initEdit();
        if (!isSoftShowing()) {
            searchEt.setCursorVisible(false);
        }

        SoftKeyBoardListener.setListener(getActivity(), searchEt);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(manager);
        adapter = new PictureTimeAdapter(getActivity());
        adapter.addAll(imgEntities);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化输入框
     */
    private void initEdit() {
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    searchEt.setCompoundDrawables(null, null, null, null);
                    searchEt.setGravity(Gravity.CENTER);
                } else {
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_search);
                    //设置边界
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    searchEt.setCompoundDrawables(drawable, null, null, null);
                    searchEt.setGravity(Gravity.LEFT);
                }
            }
        });
    }

    @Override
    protected void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("cdate", "");
        OkHttpUtils.getInstance().okPost(getActivity(), ApiConstant.BOX_LIST_IMG, params, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        searchEt.setCursorVisible(false);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        Log.d("ccnb1",response);
        if (ApiConstant.BOX_LIST_IMG.equals(requestUrl)) {
            imgEntities = ImgUtil.getTimeImg(response);
            if (null != imgEntities) {
               adapter.clear();
               adapter.addAll(imgEntities);
               adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFailed() {
        Log.d("ccnb1","in");
    }
}
