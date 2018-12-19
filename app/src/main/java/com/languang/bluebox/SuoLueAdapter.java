package com.languang.bluebox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;

public class SuoLueAdapter extends RecyclerView.Adapter<SuoLueAdapter.ViewHolder> {
    Context context;
    List<MediaBean> imgEntities;

    public SuoLueAdapter(Context context, List<MediaBean> imgEntities) {
        this.context = context;
        this.imgEntities = imgEntities;
    }

    @Override
    public SuoLueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item1, parent, false);
        return new SuoLueAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SuoLueAdapter.ViewHolder holder, int position) {
        Log.d("ccnb",imgEntities.get(position).getThumbnailSmallPath());
//        holder.image.setText("ss");
        Glide.with(context)
                .load(imgEntities.get(position).getOriginalPath())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imgEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
