package com.languang.bluebox.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.languang.bluebox.R;
import com.languang.bluebox.activity.login.FacilityListActivity;
import com.languang.bluebox.entity.facility.FacilityListInfo;
import com.luck.easyrecyclerview.adapter.BaseViewHolder;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class FacilityListAdapter extends RecyclerArrayAdapter<FacilityListInfo> {
    private Context context;
    private String uid;

    public FacilityListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponViewHolder(parent);
    }

    public class CouponViewHolder extends BaseViewHolder<FacilityListInfo>  {
        TextView nickName, boxName, code;
        ImageView laji, erweima;

        public CouponViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_facility_list);
            nickName = $(R.id.box_nick);
            boxName = $(R.id.box_name);
            code = $(R.id.code);
            laji = $(R.id.laji);
            erweima = $(R.id.erweima);
        }

        @Override
        public void setData(FacilityListInfo data, int position) {
            if (data.isOnline()) {
                boxName.setTextColor(context.getResources()
                        .getColor(R.color.myBlue));
                nickName.setTextColor(context.getResources()
                        .getColor(R.color.myBlue));
                laji.setImageDrawable(context.getResources()
                        .getDrawable(R.drawable.ic_laji));
                erweima.setImageDrawable(context.getResources()
                        .getDrawable(R.drawable.ic_erweima));
            } else {
                boxName.setTextColor(context.getResources()
                        .getColor(R.color.gray));
                nickName.setTextColor(context.getResources()
                        .getColor(R.color.gray));
                laji.setImageDrawable(context.getResources()
                        .getDrawable(R.drawable.ic_laji_2));
                erweima.setImageDrawable(context.getResources()
                        .getDrawable(R.drawable.ic_erweima_3));
            }
            nickName.setText(data.getNikename() + (data.isOnline() ? "(在线)" : "(离线)"));
            boxName.setText(data.getBluename());
            uid = data.getBlueuuid();
            laji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示")
                            .setMessage("是否确认删除？")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ((FacilityListActivity) context).deleteBox(uid);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
//            setNick.setOnClickListener(this);
//            code.setOnClickListener(this);
//            deleteBox.setOnClickListener(this);
//            netInfo.setOnClickListener(this);
        }

    }

    @Override
    public int getPosition(FacilityListInfo item) {
        return super.getPosition(item);
    }
}
