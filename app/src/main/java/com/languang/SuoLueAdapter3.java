package com.languang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.languang.bluebox.Onclick;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.entity.ImgEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuoLueAdapter3 extends RecyclerView.Adapter<SuoLueAdapter3.ViewHolder> {
    Context context;
    List<ImgEntity> imgEntities;
    Onclick onClickListener;

    public SuoLueAdapter3(Context context, List<ImgEntity> imgEntities, Onclick onClickListeneristener) {
        this.context = context;
        this.imgEntities = imgEntities;
        this.onClickListener = onClickListeneristener;
        for (ImgEntity imgEntity : imgEntities) {
            imgEntity.setChecked(false);
        }
    }

    @Override
    public SuoLueAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item2, parent, false);
        return new SuoLueAdapter3.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SuoLueAdapter3.ViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + imgEntities.get(position)
                        .getSmallpath() + imgEntities.get(position)
                        .getSmallname())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                        for (ImgEntity imgEntity : imgEntities) {
                            if (imgEntity == imgEntities.get(position)) {
                                imgEntity.setChecked(true);
                            } else {
                                imgEntity.setChecked(false);
                            }
                        }
                    Log.d("cctag", imgEntities.get(position)
                            .isChecked() + "");
                    if (imgEntities.get(position)
                            .isChecked()) {
                        onClickListener.click(position, 0, true, imgEntities.get(position));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgEntities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
