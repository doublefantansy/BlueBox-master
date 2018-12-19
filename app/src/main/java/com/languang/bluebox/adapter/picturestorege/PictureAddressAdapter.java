package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import com.languang.bluebox.R;

/**
 * 图库地址适配器
 *
 * @author 49829
 * @date 2018/4/16
 */

public class PictureAddressAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public PictureAddressAdapter(Context context, List<String> list) {
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
            convertView = inflater.inflate(R.layout.item_address, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }

        return convertView;
    }

    class ViewHolder {

    }
}
