package com.languang.bluebox.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.Onclick1;
import com.languang.bluebox.R;
import com.languang.bluebox.activity.property.ExportFacilityActivity;
import com.languang.bluebox.fragment.propertysheet.OutBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 光盘清单列表适配器
 *
 * @author 49829
 * @date 2018/4/8
 */
public class PropertySheetAdapter extends RecyclerView.Adapter<PropertySheetAdapter.ViewHolder> {
    private Context mContext;
    List<String> newlist = new ArrayList<>();
    List<Integer> integers = new ArrayList<>();
    List<OutBean.Nocd.ImgEntitysp> newlist1 = new ArrayList<>();
    Map<String, Map<String, OutBean.Nocd.ImgEntitysp>> newMap;
    SuoLueAdapter2 adapter1;
    Onclick1 onclick1;

    public PropertySheetAdapter(Context context, Map<String, Map<String, OutBean.Nocd.ImgEntitysp>> newMap, Onclick1 onclick1) {
        super();
        this.mContext = context;
        this.newMap = newMap;
        this.onclick1 = onclick1;
        for (Map.Entry<String, Map<String, OutBean.Nocd.ImgEntitysp>> entry : newMap.entrySet()) {
            newlist.add(entry.getKey());
            for (Map.Entry<String, OutBean.Nocd.ImgEntitysp> entry1 : entry.getValue()
                    .entrySet()) {
                newlist1.add(entry1.getValue());
            }
        }
    }

    public void change(boolean t) {
        for (OutBean.Nocd.ImgEntitysp imgEntitysp : newlist1) {
            imgEntitysp.setClick(t == true ? 0 : 1);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_property_sheet, parent, false);
        return new PropertySheetAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.serial_tv.setText(newlist.get(position));
        holder.id.setText(":(" + newMap.get(newlist.get(position))
                .size() + ")");
        holder.state.setText(newMap.get(newlist.get(position))
                .size() + "");
        holder.right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<OutBean.Nocd.ImgEntitysp> list = new ArrayList<>();
                for (Map.Entry<String, OutBean.Nocd.ImgEntitysp> entry : newMap.get(newlist.get(position))
                        .entrySet()) {
                    list.add(entry.getValue());
                }
                Intent intent = new Intent(mContext, ExportFacilityActivity.class);
                intent.putExtra("spe", new Gson().toJson(list));
                intent.putExtra("spe1", list.size());
                mContext.startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(layoutManager);
        adapter1 = new SuoLueAdapter2(mContext, newlist1, onclick1);
        holder.recyclerView.setAdapter(adapter1);
    }

    @Override
    public int getItemCount() {
        return newMap.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout)
        RelativeLayout layout;
        @BindView(R.id.serial_num)
        TextView id;
        @BindView(R.id.state)
        TextView state;
        @BindView(R.id.serial_tv)
        TextView serial_tv;
        @BindView(R.id.right_iv)
        ImageView right_iv;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;
//        @BindView(R.id.first_image)
//        ImageView first_image;
//        @BindView(R.id.second_image)
//        ImageView second_image;
//        @BindView(R.id.third_image)
//        ImageView third_image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
