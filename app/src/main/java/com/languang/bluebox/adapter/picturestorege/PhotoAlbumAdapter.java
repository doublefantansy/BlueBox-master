package com.languang.bluebox.adapter.picturestorege;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.easyrecyclerview.adapter.BaseViewHolder;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;


import java.io.File;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.utils.GlideUtils;
import com.languang.bluebox.utils.image.ImageBucket;

/**
 * 相册列表适配器
 * on 2018/4/16.
 *
 * @author 49829
 */

public class PhotoAlbumAdapter extends RecyclerArrayAdapter<ImageBucket> {

    private Context context;
    private TimeGridAdapter adapter;

    public PhotoAlbumAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponViewHolder(parent);
    }

    public class CouponViewHolder extends BaseViewHolder<ImageBucket> {

        ImageView albumImage;
        TextView albumName, albumNum;

        public CouponViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_photo_album);
            albumImage = $(R.id.album_image);
            albumName = $(R.id.album_name);
            albumNum = $(R.id.album_num);
        }

        @Override
        public void setData(ImageBucket data, int position) {
            if (data.getImageList().size() > 0) {
                Glide.with(context).asBitmap().load(new File(data.getImageList().get(0).getImagePath())).into(albumImage);
            }
            albumName.setText(data.getBucketName());

            albumNum.setText(data.getImageList().size() + "张");

        }

    }

    @Override
    public int getPosition(ImageBucket item) {
        return super.getPosition(item);
    }
}
