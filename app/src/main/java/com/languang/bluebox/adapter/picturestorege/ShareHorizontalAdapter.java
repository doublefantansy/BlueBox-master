package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mrj.framworklib.utils.ScreenUtilBase;

import java.util.List;

import com.languang.bluebox.R;

/**
 * 分享页面 横向图片列表适配器
 *
 * @author 49829
 * @date 2018/4/12
 */

public class ShareHorizontalAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;
    private int selectedPosition = 0;

    public ShareHorizontalAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    public void selectImage(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_share_list, null);
            holder = new ViewHolder();
            holder.shareListLl = convertView.findViewById(R.id.share_list_ll);
            ImageView imageView = new ImageView(context);
            holder.shareListLl.addView(imageView);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            if (position == selectedPosition) {
                layoutParams.width = ScreenUtilBase.getScreenWidth(context) / 5;
            } else {
                layoutParams.width = ScreenUtilBase.getScreenWidth(context) / 7;
            }
            layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
            imageView.setLayoutParams(layoutParams);
            switch (position) {
                case 0:
                    imageView.setBackgroundResource(R.mipmap.meinv);
                    break;
                case 1:
                    imageView.setBackgroundResource(R.mipmap.p1);
                    break;
                case 2:
                    imageView.setBackgroundResource(R.mipmap.p2);
                    break;
                case 3:
                    imageView.setBackgroundResource(R.mipmap.p4);
                    break;
                default:
            }
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        return convertView;
    }

    class ViewHolder {
        LinearLayout shareListLl;
    }


    private int getPicWidth(int position) {
        Bitmap bitmap = null;
        switch (position) {
            case 0:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.meinv);
                break;
            case 1:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.p1);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.p2);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.p4);
                break;
            default:
        }
        int px = ScreenUtilBase.dip2px(context, 75f);

        return bitmap.getWidth() / (bitmap.getHeight() / px);
    }

}
