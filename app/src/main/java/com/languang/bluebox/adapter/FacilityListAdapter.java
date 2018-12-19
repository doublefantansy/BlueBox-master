package com.languang.bluebox.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
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

    public class CouponViewHolder extends BaseViewHolder<FacilityListInfo> implements View.OnClickListener {
        TextView nickName, boxName, setNick, code, deleteBox, netInfo;

        public CouponViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_facility_list);
            nickName = $(R.id.box_nick);
            boxName = $(R.id.box_name);
            setNick = $(R.id.set_nick);
            code = $(R.id.code);
            deleteBox = $(R.id.delete_box);
            netInfo = $(R.id.net_info);
        }

        @Override
        public void setData(FacilityListInfo data, int position) {
            nickName.setText(data.getNikename());
            boxName.setText(data.getBluename());
            uid = data.getBlueuuid();
            setNick.setOnClickListener(this);
            code.setOnClickListener(this);
            deleteBox.setOnClickListener(this);
            netInfo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final FacilityListActivity activity = (FacilityListActivity) context;
            switch (v.getId()) {
                case R.id.set_nick:
                    activity.showDialog(uid);
                    break;
                case R.id.code:
                    break;
                case R.id.delete_box:
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示").setMessage("是否确认删除？")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    activity.deleteBox(uid);
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    break;
                case R.id.net_info:
                    break;
            }
        }
    }

    @Override
    public int getPosition(FacilityListInfo item) {
        return super.getPosition(item);
    }


}
