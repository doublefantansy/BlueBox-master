package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.mrj.framworklib.utils.ScreenUtilBase;

import java.util.List;

import com.languang.bluebox.R;

/**
 * 浏览图片适配器
 *
 * @author 49829
 * @date 2018/4/17
 */

public class BrowseGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public BrowseGridAdapter(Context context, List<String> list) {
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
            convertView = inflater.inflate(R.layout.item_browse_grid, null);
            holder = new ViewHolder();
            holder.relativeLayout = convertView.findViewById(R.id.browse_grid_rl);
            ImageView imageView = new ImageView(context);
            holder.relativeLayout.addView(imageView);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.width = (ScreenUtilBase.getScreenWidth(context) / 4 - 2);
            layoutParams.height = (ScreenUtilBase.getScreenWidth(context) / 4 - 2);
            imageView.setLayoutParams(layoutParams);
            Glide.with(context).asBitmap().load(R.mipmap.meinv).into(imageView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        return convertView;
    }

    class ViewHolder {
        LinearLayout relativeLayout;
    }

}
