package com.languang.bluebox.fragment.facility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.languang.bluebox.R;
import com.languang.bluebox.entity.facility.FacilityListInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInfoDataAdapter extends RecyclerView.Adapter<MyInfoDataAdapter.ViewHolder> {
    Context context;
    List<FacilityListInfo> list;

    public MyInfoDataAdapter(Context context, List<FacilityListInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.infolist, parent, false);
            return new MyInfoDataAdapter.ViewHolder(view);
        } else if (viewType == 0) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.infolist1, parent, false);
            return new MyInfoDataAdapter.ViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position)
                .isOnline()==true?1:0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.get(position)
                .isOnline()) {
            holder.title.setText(list.get(position)
                    .getBluename() + "(在线)");
        } else {
            holder.title.setText(list.get(position)
                    .getBluename() + "(离线)");
        }
        holder.name.setText(list.get(position)
                .getNikename());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
