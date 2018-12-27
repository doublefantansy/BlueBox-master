package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
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
 * Created by 49829 on 2018/4/16.
 */
public class PictureTagAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    List<String> strings = new ArrayList<>();
    List<ImgEntity> imgEntityList = new ArrayList<>();
    Map<String, ImgEntity> imgEntities;

    public PictureTagAdapter(Context context, Map<String, ImgEntity> imgEntities) {
        this.context = context;
        this.imgEntities = imgEntities;
        for (Map.Entry<String, ImgEntity> entry : imgEntities.entrySet()) {
            strings.add(entry.getKey());
            imgEntityList.add(entry.getValue());
        }
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imgEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return imgEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_tag_grid, null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.text);
            holder.imageView = convertView.findViewById(R.id.iam);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.textView.setText("#" + strings.get(position));
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + imgEntityList.get(position)
                        .getSmallpath() + imgEntityList.get(position)
                        .getSmallname())
                .into(holder.imageView);
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
