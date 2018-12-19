package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.languang.bluebox.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 浏览列表子项适配器
 *
 * @author 49829
 * @date 2018/4/17
 */

public class BrowseListAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    private BrowseGridAdapter adapter;

    public BrowseListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_browse_list, null);
            holder = new ViewHolder();
            holder.myGridView = convertView.findViewById(R.id.my_grid);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < position + 5; i++) {
            list.add("");
        }
        adapter = new BrowseGridAdapter(context, list);
        holder.myGridView.setAdapter(adapter);
        return convertView;
    }

    class ViewHolder {
        GridView myGridView;
    }

}
