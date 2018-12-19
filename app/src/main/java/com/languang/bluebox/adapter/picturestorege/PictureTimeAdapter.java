package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.R;
import com.languang.bluebox.activity.picturestorege.ShareActivity;
import com.languang.bluebox.entity.ImgListEntity;
import com.luck.easyrecyclerview.adapter.BaseViewHolder;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * 时间图库适配器
 *
 * @author 49829
 * @date 2018/4/12
 */
public class PictureTimeAdapter extends RecyclerArrayAdapter<ImgListEntity> {
    private Context context;
    private TimeGridAdapter adapter;
//    private List<ImgListEntity> list;

    public PictureTimeAdapter(Context context) {
        super(context);
        this.context = context;
//        this.list = list;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponViewHolder(parent);
    }

    public class CouponViewHolder extends BaseViewHolder<ImgListEntity> {
        TextView time;
        GridView myGridView;

        public CouponViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_picture_time);
            time = $(R.id.time);
            myGridView = $(R.id.my_grid);
        }

        @Override
        public void setData(final ImgListEntity data, int position) {
            time.setText(data.getTime());
            adapter = new TimeGridAdapter(context, data.getImgEntityList());
            myGridView.setAdapter(adapter);
//            final int headerP = position;
            myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("ccnb", data.getImgEntityList() + "");
                    Intent intent = new Intent(context, ShareActivity.class);
                    intent.putExtra("ima", new Gson().toJson(data));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getPosition(ImgListEntity item) {
        return super.getPosition(item);
    }
}
