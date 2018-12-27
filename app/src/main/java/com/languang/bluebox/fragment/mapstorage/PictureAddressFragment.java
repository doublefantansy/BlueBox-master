package com.languang.bluebox.fragment.mapstorage;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.languang.bluebox.R;
import com.languang.bluebox.adapter.picturestorege.PictureAddressAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SoftKeyBoardListener;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.coustomview.CustomEditText;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 地点图片
 *
 * @author 49829
 * @date 2018/4/12
 */
public class PictureAddressFragment extends BaseFragment implements OkHttpCallBack {
    @BindView(R.id.search_et)
    CustomEditText searchEt;
    @BindView(R.id.address_grid)
    GridView addressGrid;
//    @BindView(R.id.search_et)
//    CustomEditText searchEditText;
    @BindView(R.id.search)
    TextView search;
    PictureAddressAdapter addressAdapter;
    private Map<String, Integer> list = new HashMap<>();
    List<ImgEntity> imgEntityList = new ArrayList<>();
    List<ImgListEntity> imgEntities;

    public void setList(List<ImgListEntity> imgEntities) {
        this.imgEntities = imgEntities;
        for (ImgListEntity imgEntity : imgEntities) {
            for (ImgEntity entity : imgEntity.getImgEntityList()) {
//                Log.d("ccnb11", entity.getLocation() + "");
                boolean isCon = false;
                if (entity.getLocation() != null) {
                    for (String s : list.keySet()) {
                        if (entity.getLocation()
                                .equals(s)) {
                            isCon = true;
                        }
                    }
                    if (!isCon) {
                        if (list.containsKey(entity.getLocation())) {
                            int c = list.get(entity.getLocation());
                            list.put(entity.getLocation(), ++c);
                        } else {
                            imgEntityList.add(entity);
                            list.put(entity.getLocation(), 1);
                        }
                    }
                }
            }
        }
        addressAdapter = new PictureAddressAdapter(getActivity(), list, imgEntityList);
        addressGrid.setAdapter(addressAdapter);
//        addressGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getActivity(), ShareActivity.class));
//            }
//        });
//        for (String s : list) {
//        Log.d("ccnb11", imgEntities.size() + "");
//        Log.d("ccnb11", list.size() + "");
//        }
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_address;
    }

    @Override
    protected void initView(View view) {
        initEdit();
        SoftKeyBoardListener.setListener(getActivity(), searchEt);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              for (Map.Entry<String,Integer> entry:)
            }
        });
//        imgEntities = ((MainActivity) getActivity()).imgEntities;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchEt.setCursorVisible(false);
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
        params.put("type", "location3");
        OkHttpUtils.getInstance()
                .okPost(getActivity(), ApiConstant.BOX_LIST_TAGGED, params, this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        if (TextUtils.isEmpty(response)) {
            return;
        }
        if (ApiConstant.BOX_LIST_TAGGED.equals(requestUrl)) {
        }
    }

    @Override
    public void onFailed() {
    }
}
