package com.languang.bluebox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class MyImageVIew extends android.support.v7.widget.AppCompatImageView {
    Context context;

    public MyImageVIew(Context context) {
        super(context, null);
    }

    public MyImageVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(23);
        Bitmap mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.gou);
//        Rect dst = new Rect(-200, -200, 200, 200);
        Rect src = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        Rect dst = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        // 绘制
//        canvas.drawBitmap(mBitmap, src, dst, null);
//        canvas.drawBitmap(mBitmap, 0, 0, paint);
//        paint.setColor(getResources().getColor(R.color.balck));
//        canvas.drawText("ccnb", 0, getHeight(), paint);
    }
}
