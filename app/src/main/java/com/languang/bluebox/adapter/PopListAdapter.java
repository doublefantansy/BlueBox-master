package com.languang.bluebox.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.languang.bluebox.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopListAdapter extends RecyclerView.Adapter<PopListAdapter.ViewHolder> implements View.OnClickListener {
    Context context;
    List<String> list;
    ImgOnClickListenner imgOnClickListenner;

    public PopListAdapter(Context context, List<String> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PopListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                                  .inflate(R.layout.poplist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(i);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.mobile.setText(list.get(i)
                                      );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setMobileListen(ImgOnClickListenner imgOnClickListenner) {
        this.imgOnClickListenner = imgOnClickListenner;
    }

    @Override
    public void onClick(View view) {
        if (imgOnClickListenner != null) {
            try {
                imgOnClickListenner.onClick((Integer) view.getTag());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mobile)
        TextView mobile;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

