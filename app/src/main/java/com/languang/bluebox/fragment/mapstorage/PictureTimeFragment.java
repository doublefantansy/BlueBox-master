package com.languang.bluebox.fragment.mapstorage;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.FFInterface;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.VoiceBean;
import com.languang.bluebox.adapter.picturestorege.PictureTimeAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SoftKeyBoardListener;
import com.languang.bluebox.coustomview.CustomEditText;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.languang.bluebox.utils.ImgUtil;
import com.luck.easyrecyclerview.EasyRecyclerView;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

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
    @BindView(R.id.search)
    TextView search;
    private List<String> stringList = new ArrayList<>();
    public PictureTimeAdapter adapter;
    private List<ImgListEntity> imgEntities = new ArrayList<>();
    FFInterface ffInterface;
    FF1Interface ff1Interface;
    List<ImgListEntity> list = new ArrayList<>();
    private boolean isfirs = true;

    public void setFF(FFInterface ff) {
        this.ffInterface = ff;
    }

    public void setFF1(FF1Interface ff) {
        this.ff1Interface = ff;
    }

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
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                list.clear();
                for (ImgListEntity imgEntity : imgEntities) {
                    if (imgEntity.getTime()
                            .contains(charSequence)) {
                        list.add(imgEntity);
                    }
                    adapter.setL(list);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        SoftKeyBoardListener.setListener(getActivity(), searchEt);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(manager);
        adapter = new PictureTimeAdapter(getActivity(), ff1Interface, imgEntities);
//        adapter.addAll(imgEntities);
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
//        params.put("cdate", "");
        OkHttpUtils.getInstance()
                .okPost(getActivity(), TimeUtils.getWlanIp() + "/listimg", params, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        searchEt.setCursorVisible(false);
        Map<String, Object> params = new HashMap<>();
        OkHttpUtils.getInstance()
                .okPost(getActivity(), TimeUtils.getWlanIp() + "/listimg", params, this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        Log.d("ccnb1", response);
        if ((TimeUtils.getWlanIp() + "/listimg").equals(requestUrl)) {
            imgEntities = ImgUtil.getTimeImg(response);
//            imgEntities=new Gson().fromJson(response,new )
            if (imgEntities != null) {
                for (ImgListEntity imgEntity : imgEntities) {
                    for (ImgEntity entity : imgEntity.getImgEntityList()) {
                        if (!entity.getVoice1()
                                .equals("false")) {
                            entity.setVoice((VoiceBean) new Gson().fromJson(entity.getVoice1(), new TypeToken<VoiceBean>() {
                            }.getType()));
                        }
                    }
                }
                if (null != imgEntities) {
                    adapter.setL(imgEntities);
                }
                ffInterface.click(imgEntities);
            }
        }
    }

    @Override
    public void onFailed() {
        Log.d("ccnb1", "in");
    }
}
