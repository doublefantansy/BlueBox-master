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
import com.languang.bluebox.fragment.mapstorage.AdressInterface;

import java.util.HashMap;
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
    private LayoutInflater inflater;
    //    List<String> strings1;
    //    List<Integer> integers;
//    Map<String, List<ImgEntity>> imgEntities;
    Map<String, ImgListEntity> listEntity = new HashMap<>();
    List<String> strings;
    AdressInterface ffInterface;
    boolean allCheak;
    boolean is;
    int count = 0;

    public PictureAddressAdapter(Context context, Map<String, List<ImgEntity>> imgEntities, List<String> strings, AdressInterface ff1Interface) {
//        super();
        this.context = context;
//        this.imgEntities = imgEntities;
        for (Map.Entry<String, List<ImgEntity>> entry : imgEntities.entrySet()) {
            ImgListEntity imgListEntity = new ImgListEntity();
            imgListEntity.setImgEntityList(entry.getValue());
            listEntity.put(entry.getKey(), imgListEntity);
        }
        this.strings = strings;
//        this.integers = integers;
        this.ffInterface = ff1Interface;
//        Log.d("ccnbccnbccnb2", integers.size() + "");
        inflater = LayoutInflater.from(context);
//        Log.d("ccnbccc", "in");
    }

    public void setImgEntities(Map<String, ImgListEntity> imgEntities) {
        this.listEntity = imgEntities;
        strings.clear();
        for (Map.Entry<String, ImgListEntity> entry : this.listEntity.entrySet()) {
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

    public void clear() {
        for (Map.Entry<String, ImgListEntity> entry : listEntity.entrySet()) {
            for (ImgEntity imgEntity : entry.getValue()
                    .getImgEntityList()
                    ) {
                imgEntity.setChecked(false);
            }
        }
        count = 0;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        count = 0;
        View view;
//        Log.d("ccnbccnbccnb", "getView: position = " + position);
        ViewHolder holder = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_address, parent, false);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.text);
            holder.count = view.findViewById(R.id.count);
            holder.ima = view.findViewById(R.id.ima);
            holder.gou = view.findViewById(R.id.gou);
            holder.layout = view.findViewById(R.id.layout);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(strings.get(position));
        holder.count.setText(listEntity.get(strings.get(position))
                .getImgEntityList()
                .size()
                + "张");
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + listEntity.get(strings.get(position))
                        .getImgEntityList()
                        .get(0)
                        .getSmallpath() + listEntity.get(strings.get(position))
                        .getImgEntityList()
                        .get(0)
                        .getSmallname())
                .into(holder.ima);
        allCheak = false;
        for (ImgEntity imgEntity : listEntity.get(strings.get(position))
                .getImgEntityList()) {
            if (!imgEntity.isChecked()) {
                allCheak = false;
                break;
            } else {
                allCheak = true;
            }
        }
//            Log.d("cctag", "in");
        holder.gou.setVisibility(allCheak == true ? View.VISIBLE : View.INVISIBLE);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DaoChuActivity.class);
                intent.putExtra("spe", new Gson().toJson(listEntity.get(strings.get(position))));
                context.startActivity(intent);
//                    for (ImgEntity imgEntity : listEntity.get(strings.get(position))
//                            .getImgEntityList()
//                            ) {
//                        Log.d("ccmn", imgEntity.isChecked() + "");
//                        imgEntity.setChecked(!imgEntity.isChecked());
//                        is = imgEntity.isChecked();
//                        if (!imgEntity.isChecked()) {
////                        allCheak = false;
//                            count--;
//                        } else {
//                            count++;
//                        }
//                    }
//                    ffInterface.click(is, count, listEntity.get(strings.get(position))
//                            .getImgEntityList());
//                    notifyDataSetChanged();
            }
        });
        return view;
    }

    class ViewHolder {
        RelativeLayout layout;
        TextView textView;
        TextView count;
        ImageView ima;
        ImageView gou;
    }
}
