package com.languang.bluebox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.easyrecyclerview.adapter.BaseViewHolder;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;

import com.languang.bluebox.R;

/**
 * 光盘清单列表适配器
 *
 * @author 49829
 * @date 2018/4/8
 */

public class PropertySheetAdapter extends RecyclerArrayAdapter<String> {

    private Context mContext;

    public PropertySheetAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponViewHolder(parent);
    }

    public class CouponViewHolder extends BaseViewHolder<String> {

        TextView serialNum, state;
        ImageView firstIv, secondIv, thirdIv;

        public CouponViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_property_sheet);
            serialNum = $(R.id.serial_num);
            state = $(R.id.state);
            firstIv = $(R.id.first_image);
            secondIv = $(R.id.second_image);
            thirdIv = $(R.id.third_image);
        }

        @Override
        public void setData(String data, int position) {
            serialNum.setText(data);
        }
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
    }
}
