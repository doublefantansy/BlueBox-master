package com.languang.bluebox.basework.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

/**
 * 加载圆型图标
 *
 * @author 49829
 * @date 2017/11/6
 */

public class GlideUtils {

    /**
     * 加载显示图片
     *
     * @param context 活动
     * @param iv      imageView
     * @param url     地址
     */
    public static void loadImage(final Context context, final ImageView iv, String url) {
        loadImage(context, iv, url, null);
    }

    /**
     * 加载显示图片
     *
     * @param context 活动
     * @param iv      imageView
     * @param url     地址
     * @param file    文件
     */
    private static void loadImage(final Context context, final ImageView iv, String url, File file) {
        boolean isFile = "".equals(url) || null == url && file != null;
        boolean isUrl = file == null && !"".equals(url) || null != url;
        if (isFile) {
            Glide.with(context)
                    .asBitmap()
                    .load(file)
                    .into(new BitmapImageViewTarget(iv) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            iv.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } else if (isUrl) {
            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .into(new BitmapImageViewTarget(iv) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            iv.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }
    }

}
