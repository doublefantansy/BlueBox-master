package com.languang.bluebox.activity.picturestorege;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.luck.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.languang.bluebox.R;
import com.languang.bluebox.adapter.picturestorege.BrowseAdapter;
import com.languang.bluebox.adapter.picturestorege.UsbAdapter;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 浏览Sd卡
 *
 * @author 49829
 * @date 2018/4/17
 */

public class BrowseSdActivity extends BaseFragmentActivity {

    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;
    private BrowseAdapter adapter;
    private List<Double> list = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_browse_sd;
    }

    @Override
    protected void initView() {
        setTitle("SD卡1");
        setRightText("选择");
        setRightOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new BrowseAdapter(mContext);
        recyclerView.setAdapter(adapter);
        list.add(0.32);
        list.add(0.62);
        adapter.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
