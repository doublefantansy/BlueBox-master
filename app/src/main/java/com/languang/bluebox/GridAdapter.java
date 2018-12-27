package com.languang.bluebox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.languang.bluebox.entity.ImgEntity;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<ImgEntity> list;
    CountInterface countInterface;
//    List<CheckBox> checkBoxes = new ArrayList<>();

    public GridAdapter(Context context, List<ImgEntity> list, CountInterface countInterface) {
        this.context = context;
        this.countInterface = countInterface;
        this.list = list;
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
//        checkBoxes.add(holder.gou);
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + list.get(position)
                        .getSmallpath() + list.get(position)
                        .getSmallname())
                .into(holder.imageView);
//        list.get(position)
//                .setLinearLayout(holder.frameLayout);
//        newList.get(position)
//                .setGou(holder.gou);
//        newList.get(position)
//                .setImageView(holder.imageView);
        list.get(position)
                .setChecked(list.get(position)
                        .isChecked());
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position)
                        .setChecked(!list.get(position)
                                .isChecked());
                if (list.get(position)
                        .isChecked()) {
                    holder.gou.setVisibility(View.VISIBLE);
                    PictureStoreageFragment.count++;
                    countInterface.click(true, PictureStoreageFragment.count, list.get(position));
                } else {
                    PictureStoreageFragment.count--;
                    countInterface.click(false, PictureStoreageFragment.count, list.get(position));
                    holder.gou.setVisibility(View.INVISIBLE);
                }
//                holder.gou.setChecked(list.get(position)
//                        .isChecked());
            }
        });
        return convertView;
    }
//    public void open(final int p) {
//        Log.d("ccnb", p + "");
////        Log.d("ccnb", list.get(p)
////                .isCheaked() + "");
////        notifyDataSetChanged();
////        if (newList.get(p)
////                .isCheaked()) {
////        } else {
////            newList.get(p)
////                    .getGou()
////                    .setAlpha(1f);
////        }
////        list.get(p)
////                .setCheaked(!list.get(p)
////                        .isCheaked());
//        notifyDataSetChanged();
//    }

    public void begin() {
//            Log.d("ccnb",   "int");
//            for (CheckBox checkBox : checkBoxes) {
//                checkBox.setVisibility(View.VISIBLE);
//                checkBox.setClickable(true);
//            }
    }

    class ViewHolder {
        RelativeLayout frameLayout;
        ImageView imageView;
        ImageView gou;
    }
}
