package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.languang.bluebox.DaoChuActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.languang.bluebox.fragment.mapstorage.TagInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 49829 on 2018/4/16.
 */
public class PictureTagAdapter extends BaseAdapter {
    private Context context;
    List<String> strings = new ArrayList<>();
    List<ImgEntity> imgEntityList = new ArrayList<>();
    Map<String, ImgListEntity> imgEntities;
    private boolean allCheak;
    private int count = 0;
    TagInterface adressInterface;
    private boolean is;

    public PictureTagAdapter(Context context, Map<String, ImgListEntity> imgEntities, TagInterface adressInterface) {
        this.context = context;
        this.imgEntities = imgEntities;
        this.adressInterface = adressInterface;
        for (Map.Entry<String, ImgListEntity> entry : imgEntities.entrySet()) {
            strings.add(entry.getKey());
        }
    }

    public void setImgEntityList(Map<String, ImgListEntity> imgEntities) {
        this.imgEntities = imgEntities;
        strings.clear();
        for (Map.Entry<String, ImgListEntity> entry : this.imgEntities.entrySet()) {
            strings.add(entry.getKey());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //    public static <A> List<A> removeDuplicate(List<A> list) {
//        Set set = new LinkedHashSet<A>();
//        set.addAll(list);
//        list.clear();
//        list.addAll(set);
//        return list;
//    }
    public void clear() {
        for (Map.Entry<String, ImgListEntity> entry : imgEntities.entrySet()) {
            entry.getValue()
                    .setChecked(false);
        }
        count = 0;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_tag_grid, null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.text);
            holder.imageView = convertView.findViewById(R.id.iam);
            holder.gou = convertView.findViewById(R.id.gou);
            holder.layout = convertView.findViewById(R.id.layout);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.textView.setText("#" + strings.get(position));
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + imgEntities.get(strings.get(position))
                        .getImgEntityList()
                        .get(0)
                        .getSmallpath() + imgEntities.get(strings.get(position))
                        .getImgEntityList()
                        .get(0)
                        .getSmallname())
                .into(holder.imageView);
        allCheak = false;
//        imgEntities.get(strings.get(position))
//        for (ImgEntity imgEntity : imgEntities.get(strings.get(position))
//                .getImgEntityList()) {
        if (!imgEntities.get(strings.get(position))
                .isChecked()) {
            allCheak = false;
        } else {
            allCheak = true;
//                count++;
        }
//        if (allCheak) {
        holder.gou.setVisibility(allCheak == true ? View.VISIBLE : View.INVISIBLE);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DaoChuActivity.class);
                intent.putExtra("spe", new Gson().toJson(imgEntities.get(strings.get(position))));
                context.startActivity(intent);
//                boolean isCheak = false;
//                         {
//                    Log.d("ccmn", imgEntity.isChecked() + "");
//                imgEntities.get(strings.get(position))
//                        .setChecked(!imgEntities.get(strings.get(position))
//                                .isChecked());
//                is = imgEntities.get(strings.get(position))
//                        .isChecked();
//                if (!imgEntities.get(strings.get(position))
//                        .isChecked()) {
////                        allCheak = false;
//                    count--;
////                        break;
//                } else {
////                        allCheak = true;
//                    count++;
//                }
//                adressInterface.click(is, count, imgEntities.get(strings.get(position)));
//                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
        ImageView gou;
        RelativeLayout layout;
    }
}
