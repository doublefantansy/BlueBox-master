package com.languang;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.PixelFormat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.languang.bluebox.GrayFilter;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.entity.ImgEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class LvJingAdapter extends RecyclerView.Adapter<LvJingAdapter.ViewHolder> {
    Context context;
    ImgEntity imgEntity;
    ColorMatrix colorMatrix;
    List<float[]> list;
    LvjingInterface lvjingInterface;

    public LvJingAdapter(Context context, ImgEntity imgEntity, LvjingInterface lvjingInterface) {
        this.context = context;
        this.imgEntity = imgEntity;
        this.lvjingInterface = lvjingInterface;
        list = new ArrayList<>();
        list.add(new float[]{
                0.393f, 0.769f, 0.189f, 0, 0,
                0.349f, 0.686f, 0.168f, 0, 0,
                0.272f, 0.534f, 0.131f, 0, 0,
                0, 0, 0, 1, 0
        });
        list.add(new float[]{
                2, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });
        list.add(new float[]{
                1, 0, 0, 0, 0,
                0, (float) 1.4, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });
        list.add(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, (float) 1.6, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public void set1(ImgEntity imgEntity) {
        this.imgEntity = imgEntity;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.lvjing, parent, false);
        return new LvJingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + imgEntity
                        .getSrcpath() + imgEntity
                        .getSrcname())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        holder.image.setImageBitmap(GrayFilter.changeToGray(resource, new ColorMatrix(list.get(position))));
                    }
                });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = Bitmap.createBitmap(
                        holder.image.getDrawable()
                                .getIntrinsicWidth(),
                        holder.image.getDrawable()
                                .getIntrinsicHeight(),
                        holder.image.getDrawable()
                                .getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bitmap);
                holder.image.getDrawable()
                        .setBounds(0, 0, holder.image.getDrawable()
                                .getIntrinsicWidth(), holder.image.getDrawable()
                                .getIntrinsicHeight());
                holder.image.getDrawable()
                        .draw(canvas);
                lvjingInterface.click(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.layout)
        LinearLayout layout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
