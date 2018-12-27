package com.languang.bluebox.fragment.mapstorage;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.R;
import com.languang.bluebox.adapter.picturestorege.PictureTagAdapter;
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
 * 标签图片库
 *
 * @author 49829
 * @date 2018/4/12
 */
public class PictureTagFragment extends BaseFragment implements OkHttpCallBack {
    @BindView(R.id.search_et)
    CustomEditText searchEt;
    @BindView(R.id.tag_grid)
    GridView tagGrid;
    List<String> list = new ArrayList<>();
    PictureTagAdapter adapter;
    List<ImgListEntity> imgEntities;
    Map<String, ImgEntity> entityList = new HashMap<>();
    Map<String, String> map1 = new HashMap<>();

    public void setList(List<ImgListEntity> imgEntities) {
        this.imgEntities = imgEntities;
        for (ImgListEntity imgEntity : imgEntities) {
            for (ImgEntity entity : imgEntity.getImgEntityList()) {
                Map<String, String> map = new Gson().fromJson(entity.getTags(), new TypeToken<Map<String, String>>() {
                }.getType());
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (!map1.containsKey(entry.getKey())) {
                        entityList.put(entry.getValue(), entity);
                    }
                }
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    map1.putAll(map);
                }
            }
        }
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            list.add(entry.getValue());
        }
//        adapter.notifyDataSetChanged();
        Log.d("ccnb", map1 + "");
        Log.d("ccnb", map1.size() + "");
        Log.d("ccnb", entityList + "");
        Log.d("ccnb", entityList.size() + "");
//        adapter = new PictureTagAdapter(getActivity(), map1);
//        tagGrid.setAdapter(adapter);
//        Log.d("ccnb")
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_tag;
    }

    @Override
    protected void initView(View view) {
        initEdit();
        searchEt.setCursorVisible(false);
        SoftKeyBoardListener.setListener(getActivity(), searchEt);
        Log.d("ccnb", getActivity() + "");
        adapter = new PictureTagAdapter(getActivity(), entityList);
        tagGrid.setAdapter(adapter);
//        tagGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getActivity(), ShareActivity.class));
//            }
//        });
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
        params.put("type", "tag4");
        OkHttpUtils.getInstance()
                .okPost(getActivity(), ApiConstant.BOX_LIST_TAGGED, params, this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
    }

    @Override
    public void onFailed() {
    }
}
