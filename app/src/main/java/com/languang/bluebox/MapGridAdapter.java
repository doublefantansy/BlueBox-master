package com.languang.bluebox;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;

public class MapGridAdapter extends BaseAdapter {
    private Context context;
    public ImgListEntity list;
    CountInterface countInterface;
//    List<CheckBox> checkBoxes = new ArrayList<>();

    public MapGridAdapter(Context context, CountInterface countInterface) {
        this.context = context;
        this.countInterface = countInterface;
        this.list = list;
    }

    public void setList(ImgListEntity list) {
        this.list = list;
        clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.getImgEntityList()
                .size();
    }

    @Override
    public Object getItem(int position) {
        return list.getImgEntityList()
                .get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        for (ImgEntity imgEntity : list.getImgEntityList()) {
            imgEntity.setChecked(false);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_time_grid, null);
            holder = new ViewHolder();
            holder.frameLayout = convertView.findViewById(R.id.frame);
            holder.gou = convertView.findViewById(R.id.gou);
//            holder.gou.setAlpha(0f);
            holder.imageView = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + list.getImgEntityList()
                        .get(position)
                        .getSmallpath() + list.getImgEntityList()
                        .get(position)
                        .getSmallname())
                .into(holder.imageView);
        holder.gou.setVisibility(list.getImgEntityList()
                .get(position)
                .isChecked() == true ? View.VISIBLE : View.INVISIBLE);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DaoChuActivity.class);
                intent.putExtra("spe",new Gson().toJson(list));
                context.startActivity(intent);
//                list.getImgEntityList()
//                        .get(position)
//                        .setChecked(!list.getImgEntityList()
//                                .get(position)
//                                .isChecked());
//                if (list.getImgEntityList()
//                        .get(position)
//                        .isChecked()) {
//                    holder.gou.setVisibility(View.VISIBLE);
//                    MapStorageFragment.count++;
//                    countInterface.click(true, MapStorageFragment.count, list.getImgEntityList()
//                            .get(position));
//                } else {
//                    MapStorageFragment.count--;
//                    countInterface.click(false, MapStorageFragment.count, list.getImgEntityList()
//                            .get(position));
//                    holder.gou.setVisibility(View.INVISIBLE);
//                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        RelativeLayout frameLayout;
        ImageView imageView;
        ImageView gou;
    }
}
