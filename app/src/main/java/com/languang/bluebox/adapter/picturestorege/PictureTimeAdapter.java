package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.CountInterface;
import com.languang.bluebox.MapGridAdapter;
import com.languang.bluebox.R;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.languang.bluebox.fragment.mapstorage.FF1Interface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间图库适配器
 *
 * @author 49829
 * @date 2018/4/12
 */
public class PictureTimeAdapter extends RecyclerView.Adapter<PictureTimeAdapter.CouponViewHolder> {
    private Context context;
    public MapGridAdapter adapter;
    FF1Interface ff1Interface1;
    private List<ImgListEntity> list;

    /**
     * @param context
     * @param ff1Interface
     * @param imgEntities
     */
    public PictureTimeAdapter(Context context, FF1Interface ff1Interface, List<ImgListEntity> imgEntities) {
        super();
        this.context = context;
        this.ff1Interface1 = ff1Interface;
        this.list = imgEntities;
    }

    public void setL(List<ImgListEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public CouponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_picture_time, parent, false);
        return new PictureTimeAdapter.CouponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CouponViewHolder holder, int position) {
        holder.time.setText(list.get(position)
                .getTime()
                .split("-")[0] + "年" + list.get(position)
                .getTime()
                .split("-")[1] + "月" + list.get(position)
                .getTime()
                .split("-")[2] + "日");
        adapter = new MapGridAdapter(context, new CountInterface() {
            @Override
            public void click(boolean isadd, int count, ImgEntity imgEntity) {
                if (isadd) {
                    ff1Interface1.click(false, isadd, imgEntity);
                } else {
                    if (count == 0) {
                        ff1Interface1.click(true, isadd, imgEntity);
                    } else {
                        ff1Interface1.click(false, isadd, imgEntity);
                    }
                }
            }
        }, list.get(position)
                .getImgEntityList());
        holder.myGridView.setAdapter(adapter);
        holder.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ShareActivity1.class);
                intent.putExtra("ima", new Gson().toJson(list.get(position)));
                context.startActivity(intent);
//                    for (ImgEntity imgEntity : adapter.list) {
//                        imgEntity
//                    }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CouponViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.my_grid)
        GridView myGridView;

        CouponViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
