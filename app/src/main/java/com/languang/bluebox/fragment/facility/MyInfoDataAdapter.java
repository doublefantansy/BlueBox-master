package com.languang.bluebox.fragment.facility;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.languang.bluebox.R;
import com.languang.bluebox.SetNameInterface;
import com.languang.bluebox.SettingPassWordActivity;
import com.languang.bluebox.activity.initialize.SettingWanActivity;
import com.languang.bluebox.entity.facility.FacilityListInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInfoDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<FacilityListInfo> list;
    SetNameInterface setNameInterface;
    click click;
    click1 clic1k;
    infoClick infoClick;


    public MyInfoDataAdapter(Context context, List<FacilityListInfo> list, SetNameInterface setNameInterface, click click, click1 clic1k,infoClick infoClick) {
        this.context = context;
        this.list = list;
        this.setNameInterface = setNameInterface;
        this.click = click;
        this.clic1k = clic1k;
        this.infoClick=infoClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.infolist, parent, false);
            return new MyInfoDataAdapter.ViewHolder(view);
        } else if (viewType == 0) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.infolist1, parent, false);
            return new MyInfoDataAdapter.ViewHolder1(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position)
                .isOnline() == true ? 1 : 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (list.get(position)
                .isOnline()) {
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.title.setText(list.get(position)
                    .getBluename() + "(在线)");
            holder1.passWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, SettingPassWordActivity.class));
                }
            });
            holder1.device.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoClick.click(position);
//                    context.startActivity(new Intent(context, InfoActivity.class));
                }
            });
            holder1.net.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, SettingWanActivity.class);
                    intent.putExtra("a","1");
                    context.startActivity(intent);
                }
            });
            holder1.rename.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setNameInterface.click();
                }
            });
            holder1.name.setText(list.get(position)
                    .getNikename());
            holder1.erweima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.click(position);
                }
            });
            holder1.code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clic1k.click(position);
                }
            });
        } else {
            ViewHolder1 holder2 = (ViewHolder1) holder;
            holder2.title.setText(list.get(position)
                    .getBluename() + "(离线)");
            holder2.name.setText(list.get(position)
                    .getNikename());
            holder2.erweima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.click(position);
                }
            });
        }
    }

    interface click {
        void click(int p);
    }

    interface click1 {
        void click(int p);
    }
    interface infoClick {
        void click(int p);
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
        @BindView(R.id.device)
        LinearLayout device;
        @BindView(R.id.passWord)
        LinearLayout passWord;
        @BindView(R.id.net)
        LinearLayout net;
        @BindView(R.id.code)
        LinearLayout code;
        @BindView(R.id.rename)
        LinearLayout rename;
        @BindView(R.id.erweima)
        ImageView erweima;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.erweima)
        ImageView erweima;

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
