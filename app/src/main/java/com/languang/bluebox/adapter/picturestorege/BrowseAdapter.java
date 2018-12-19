package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;

import android.view.ViewGroup;


import com.luck.easyrecyclerview.adapter.BaseViewHolder;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import com.languang.bluebox.R;
import com.languang.bluebox.coustomview.MyListView;

/**
 * 浏览页面适配器
 *
 * @author 49829
 * @date 2018/4/17
 */

public class BrowseAdapter extends RecyclerArrayAdapter<Double> {

    private Context context;
    private BrowseListAdapter adapter;

    public BrowseAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponViewHolder(parent);
    }

    public class CouponViewHolder extends BaseViewHolder<Double> {

        MyListView myListView;

        public CouponViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_browse);
            myListView = $(R.id.my_list);
        }

        @Override
        public void setData(Double data, int position) {
            List<String> list = new ArrayList<>();
            list.add("");
            list.add("");
            list.add("");
            adapter = new BrowseListAdapter(context, list);
            myListView.setAdapter(adapter);
        }

    }

    @Override
    public int getPosition(Double item) {
        return super.getPosition(item);
    }
}
