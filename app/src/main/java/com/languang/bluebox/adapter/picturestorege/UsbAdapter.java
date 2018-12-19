package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.luck.easyrecyclerview.adapter.BaseViewHolder;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;

import com.languang.bluebox.R;
import com.languang.bluebox.activity.picturestorege.BrowseSdActivity;
import com.languang.bluebox.coustomview.CustomPercentView;

/**
 * USB-SD卡适配器
 *
 * @author 49829
 * @date 2018/4/17
 */

public class UsbAdapter extends RecyclerArrayAdapter<Double> {

    private Context context;
    private TimeGridAdapter adapter;

    public UsbAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponViewHolder(parent);
    }

    public class CouponViewHolder extends BaseViewHolder<Double> {
        TextView sdName, sdReduce;
        CustomPercentView percentView;
        Button exportAll, browse;


        public CouponViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_usb);
            sdName = $(R.id.sd_name);
            sdReduce = $(R.id.room_reduce);
            percentView = $(R.id.custom_percent_view);
            exportAll = $(R.id.export_all);
            browse = $(R.id.browse);
        }

        @Override
        public void setData(Double data, int position) {
            sdReduce.setText("已使用" + data * 256 + "G/256G");
            double[] doubles = new double[]{0, 0, data, 0};
            percentView.setPercent(doubles);

            exportAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            browse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, BrowseSdActivity.class));
                }
            });
        }

    }

    @Override
    public int getPosition(Double item) {
        return super.getPosition(item);
    }
}
