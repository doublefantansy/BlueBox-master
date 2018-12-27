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

import com.languang.bluebox.FFInterface;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.adapter.picturestorege.PictureTimeAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SoftKeyBoardListener;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.coustomview.CustomEditText;
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
    private PictureTimeAdapter adapter;
    private List<ImgListEntity> imgEntities = new ArrayList<>();
    FFInterface ffInterface;

    public void setFF(FFInterface ff) {
        this.ffInterface = ff;
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
//        params.put("cdate", "");
        OkHttpUtils.getInstance()
                .okPost(getActivity(), TimeUtils.getWlanIp() + "/listimg", params, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        searchEt.setCursorVisible(false);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        Log.d("ccnb1", response);
//        ResponseMessage<img>
        if (ApiConstant.BOX_LIST_IMG.equals(requestUrl)) {
            imgEntities = ImgUtil.getTimeImg(response);
//            ResponseMessage<NewPicture1> imgListEntityResponseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<NewPicture1>>() {
//            }.getType());
//            ffInterface.click(imgListEntityResponseMessage.getData().);
//            ((MainActivity) getActivity()).imgEntities = imgEntities;
//            imgEntities.get(0). getImgEntityList().get(0).getTags()
            if (null != imgEntities) {
                adapter.clear();
                adapter.addAll(imgEntities);
                adapter.notifyDataSetChanged();
//            }
//        }
            }
            ffInterface.click(imgEntities);
        }
    }

    @Override
    public void onFailed() {
        Log.d("ccnb1", "in");
    }
}
