package com.languang.bluebox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.languang.bluebox.R;

/**
 * 设备信息列表适配器
 *
 * @author 49829
 * @date 2018/4/8
 */

public class FacilityInfoAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflater;
    private String[] titleNames = new String[]{"设备名", "序列号", "WAN IP", "光刻机序号", "ROM版本"};

    public FacilityInfoAdapter(Context context, List<String> list) {
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
            convertView = inflater.inflate(R.layout.item_facility_info, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.facility_info_name);
            holder.detailInfo = convertView.findViewById(R.id.info_detail);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        holder.name.setText(titleNames[position]);
        holder.detailInfo.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView name, detailInfo;
    }

}
