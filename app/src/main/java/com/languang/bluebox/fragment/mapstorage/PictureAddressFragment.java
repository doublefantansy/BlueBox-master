package com.languang.bluebox.fragment.mapstorage;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.adapter.picturestorege.PictureAddressAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SoftKeyBoardListener;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.coustomview.CustomEditText;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.languang.bluebox.utils.ImgUtil;
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
    //    private Map<String, Integer> list = new HashMap<>();
//    Map<String, ImgEntity> imgEntityList = new HashMap<>();
    List<ImgEntity> choose = new ArrayList<>();
    //    List<Integer> choosei = new ArrayList<>();
    List<ImgListEntity> getImgEntities;
    List<ImgListEntity> imgEntities = new ArrayList<>();
    Map<String, List<ImgEntity>> imgListEntityMap = new HashMap<>();
    Map<String, List<ImgEntity>> searchMap = new HashMap<>();
    Map<String, Integer> si = new HashMap<>();
    AdressInterface adressInterface;
    List<String> strings=new ArrayList<>();
    boolean isfirst = true;

    public void setIn(AdressInterface adressInterface) {
        this.adressInterface = adressInterface;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_address;
    }

    @Override
    protected void initView(View view) {
        initEdit();
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchMap.clear();
                for (Map.Entry<String, List<ImgEntity>> entry : imgListEntityMap.entrySet()) {
                    if (entry.getKey()
                            .contains(charSequence)) {
                        List<ImgEntity> list = new ArrayList();
                        list.addAll(entry.getValue());
                        searchMap.put(entry.getKey(), list);
                    }
                }
                addressAdapter.setImgEntities(searchMap);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        SoftKeyBoardListener.setListener(getActivity(), searchEt);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              for (Map.Entry<String,Integer> entry:)
            }
        });
        OkHttpUtils.getInstance()
                .okPost(getActivity(), TimeUtils.getWlanIp() + "/listimg", null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        getImgEntities = ImgUtil.getTimeImg(response);
                        for (ImgListEntity imgEntity : getImgEntities) {
                            for (ImgEntity entity : imgEntity.getImgEntityList()) {
//                if (entity.getLocation() != null) {
                                if (imgListEntityMap.containsKey(entity.getLocation())) {
                                    List<ImgEntity> list = imgListEntityMap.get(entity.getLocation());
                                    list.add(entity);
                                    imgListEntityMap.put(entity.getLocation(), list);
                                    int b = si.get(entity.getLocation());
                                    b++;
                                    si.put(entity.getLocation(), b);
                                } else {
//                                    imgEntityList.put(entity.getLocation(), entity);
                                    List<ImgEntity> imgEntities1 = new ArrayList<>();
                                    imgEntities1.add(entity);
                                    si.put(entity.getLocation(), 1);
                                    imgListEntityMap.put(entity.getLocation(), imgEntities1);
                                }
//                }
                            }
                        }
                        Log.d("ccnbccnb", imgListEntityMap.size() + "");
//                        List<Integer> integers = new ArrayList<>();
                       strings  = new ArrayList<>();
                        for (Map.Entry<String, Integer> entry : si.entrySet()) {
                            strings.add(entry.getKey());
//                            integers.add(entry.getValue());
                        }
//                        Log.d("ccnbccnbccnb1", integers.size() + "");
                        addressAdapter = new PictureAddressAdapter(getActivity(), imgListEntityMap, strings, adressInterface);
                        addressGrid.setAdapter(addressAdapter);
                    }

                    @Override
                    public void onFailed() {
                    }
                });
//        imgEntities = ((MainActivity) getActivity()).imgEntities;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchEt.setCursorVisible(false);
        if (!isfirst) {
            addressAdapter.clear();
        } else {
            isfirst = false;
        }
//        addressAdapter.notifyDataSetChanged();
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
