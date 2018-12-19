package com.languang.bluebox.fragment.mapstorage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.languang.bluebox.R;
import com.languang.bluebox.activity.picturestorege.ShareActivity;
import com.languang.bluebox.adapter.picturestorege.PictureTagAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.basework.utils.SoftKeyBoardListener;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.coustomview.CustomEditText;
import com.mrj.framworklib.utils.OkHttpCallBack;

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
    private List<String> list = new ArrayList<>();
    PictureTagAdapter adapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_tag;
    }

    @Override
    protected void initView(View view) {
        initEdit();
        searchEt.setCursorVisible(false);

        SoftKeyBoardListener.setListener(getActivity(), searchEt);

        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        adapter = new PictureTagAdapter(getActivity(), list);

        tagGrid.setAdapter(adapter);

        tagGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ShareActivity.class));
            }
        });

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
        OkHttpUtils.getInstance().okPost(getActivity(), ApiConstant.BOX_LIST_TAGGED, params, this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {

    }

    @Override
    public void onFailed() {

    }
}
