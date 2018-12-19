package com.languang.bluebox.activity.picturestorege;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.luck.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.languang.bluebox.R;
import com.languang.bluebox.adapter.picturestorege.UsbAdapter;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * USB界面
 *
 * @author 49829
 * @date 2018/4/17
 */

public class UsbActivity extends BaseFragmentActivity {

    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;

    private List<Double> list = new ArrayList<>();
    private UsbAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_usb;
    }

    @Override
    protected void initView() {
        setTitle("USB");
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new UsbAdapter(mContext);
        recyclerView.setAdapter(adapter);
        list.add(0.32);
        list.add(0.62);
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

}
