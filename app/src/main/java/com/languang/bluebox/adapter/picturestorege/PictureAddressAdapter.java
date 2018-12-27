package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.entity.ImgEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 图库地址适配器
 *
 * @author 49829
 * @date 2018/4/16
 */
public class PictureAddressAdapter extends BaseAdapter {
    private Context context;
    private Map<String, Integer> list;
    private LayoutInflater inflater;
    List<String> strings = new ArrayList<>();
    List<Integer> integers = new ArrayList<>();
    List<ImgEntity> imgEntities;

    public PictureAddressAdapter(Context context, Map<String, Integer> list, List<ImgEntity> imgEntities) {
        this.context = context;
        this.list = list;
        this.imgEntities = imgEntities;
        Log.d("ccn1b", imgEntities.size() + "");
        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            strings.add(entry.getKey());
            integers.add(entry.getValue());
        }
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
            holder.textView = convertView.findViewById(R.id.text);
            holder.count = convertView.findViewById(R.id.count);
            holder.ima = convertView.findViewById(R.id.ima);
            convertView.setTag(holder);
        }
        ViewHolder holder1 = (ViewHolder) convertView.getTag();
        holder1.textView.setText(strings.get(position));
        holder1.count.setText(integers.get(position) + "张");
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + imgEntities.get(position)
                        .getSmallpath() + imgEntities.get(position)
                        .getSmallname())
                .into(holder1.ima);
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        TextView count;
        ImageView ima;
    }
}
