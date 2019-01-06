package com.languang.bluebox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.languang.bluebox.Onclick1;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.fragment.propertysheet.OutBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuoLueAdapter2 extends RecyclerView.Adapter<SuoLueAdapter2.ViewHolder> {
    Context context;
    List<OutBean.Nocd.ImgEntitysp> imgEntities;
    Onclick1 onClickListener;
    int count;

    public SuoLueAdapter2(Context context, List<OutBean.Nocd.ImgEntitysp> imgEntities, Onclick1 onClickListeneristener) {
        this.context = context;
        this.imgEntities = imgEntities;
        this.onClickListener = onClickListeneristener;
    }

    @Override
    public SuoLueAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item2, parent, false);
        return new SuoLueAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SuoLueAdapter2.ViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + imgEntities.get(position)
                        .getSmallpath() + imgEntities.get(position)
                        .getSmallname())
                .into(holder.image);
        if (imgEntities.get(position)
                .getClick() == 1) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        holder.gou.setVisibility(holder.gou.getVisibility() == View.INVISIBLE ? View.VISIBLE : View.INVISIBLE);
                        if (holder.gou.getVisibility() == View.VISIBLE) {
                            count++;
                            onClickListener.click(position, count, true, imgEntities.get(position));
                        } else {
                            count--;
                            onClickListener.click(position, count, false, imgEntities.get(position));
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return imgEntities.get(position)
                .getClick();
    }

    @Override
    public int getItemCount() {
        return imgEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.gou)
        ImageView gou;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}