package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.fragment.mapstorage.AdressInterface;

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
    Map<String, List<ImgEntity>> imgEntities;
    List<String> strings;
    AdressInterface ffInterface;
    boolean allCheak;
    boolean is;
    int count = 0;

    public PictureAddressAdapter(Context context, Map<String, List<ImgEntity>> imgEntities, List<String> strings, AdressInterface ff1Interface) {
//        super();
        this.context = context;
        this.imgEntities = imgEntities;
        this.strings = strings;
//        this.integers = integers;
        this.ffInterface = ff1Interface;
//        Log.d("ccnbccnbccnb2", integers.size() + "");
        inflater = LayoutInflater.from(context);
//        Log.d("ccnbccc", "in");
    }

    public void setImgEntities(Map<String, List<ImgEntity>> imgEntities) {
        this.imgEntities = imgEntities;
        strings.clear();
        for (Map.Entry<String, List<ImgEntity>> entry : this.imgEntities.entrySet()) {
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
        for (Map.Entry<String, List<ImgEntity>> entry : imgEntities.entrySet()) {
            for (ImgEntity imgEntity : entry.getValue()) {
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
//        if (((YourGridView) parent).isOnMeasure()) {
//            //如果是onMeasure调用的就立即返回
//            return convertView;
//        }
        holder.textView.setText(strings.get(position));
        holder.count.setText(imgEntities.get(strings.get(position))
                .size()
                + "张");
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + imgEntities.get(strings.get(position))
                        .get(0)
                        .getSmallpath() + imgEntities.get(strings.get(position))
                        .get(0)
                        .getSmallname())
                .into(holder.ima);
        allCheak = false;
        for (ImgEntity imgEntity : imgEntities.get(strings.get(position))) {
            if (!imgEntity.isChecked()) {
                allCheak = false;
//                count--;
                break;
            } else {
                allCheak = true;
//                count++;
            }
        }
//        if (allCheak) {
        holder.gou.setVisibility(allCheak == true ? View.VISIBLE : View.INVISIBLE);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                boolean isCheak = false;
                for (ImgEntity imgEntity : imgEntities.get(strings.get(position))
                        ) {
                    Log.d("ccmn", imgEntity.isChecked() + "");
                    imgEntity.setChecked(!imgEntity.isChecked());
                    is = imgEntity.isChecked();
                    if (!imgEntity.isChecked()) {
//                        allCheak = false;
                        count--;
//                        break;
                    } else {
//                        allCheak = true;
                        count++;
                    }
//                    if (!imgEntity.isChecked()) {
//                        isCheak = false;
//                        break;
//                    } else {
//                        isCheak = true;
//                    }
                }
                ffInterface.click(is, count, imgEntities.get(strings.get(position)));
                notifyDataSetChanged();
            }
        });
//        }
//        System.out.println("xcqw getView  2***position" + position);
//        holder.gou.setVisibility(list.get(position)
//                .isChecked() == true ? View.VISIBLE : View.INVISIBLE);
//        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                list.get(position)
//                        .setChecked(!list.get(position)
//                                .isChecked());
//                if (list.get(position)
//                        .isChecked()) {
//                    holder.gou.setVisibility(View.VISIBLE);
//                    MapStorageFragment.count++;
//                    countInterface.click(true, MapStorageFragment.count, list.get(position));
//                } else {
//                    MapStorageFragment.count--;
//                    countInterface.click(false, MapStorageFragment.count, list.get(position));
//                    holder.gou.setVisibility(View.INVISIBLE);
//                }
////                holder.gou.setChecked(list.get(position)
////                        .isChecked());
//            }
//        });
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
