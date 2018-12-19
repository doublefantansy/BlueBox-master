package com.languang.bluebox.coustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.languang.bluebox.R;

/**
 * 自定义内存百分比视图
 *
 * @author 49829
 * @date 2018/3/28
 */

public class CustomPercentView extends View {

    /**
     * 系统文件占比
     */
    private double systemFile = 0;
    /**
     * 图片文件占比
     */
    private double pictureFile = 0;
    /**
     * 待归档文件占比
     */
    private double rawFile = 0;
    /**
     * 临时文件占比
     */
    private double temporaryFile = 0;

    public CustomPercentView(Context context) {
        super(context);
    }

    public CustomPercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint strokePaint = new Paint();
        strokePaint.setColor(getResources().getColor(R.color.main_bg));
        strokePaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, getWidth(), getHeight()-1, strokePaint);
        Paint myPaint = new Paint();
        myPaint.setColor(getResources().getColor(R.color.color_84cb44));
        canvas.drawRect(0, 0, (float) (systemFile * getWidth()), getHeight(), myPaint);
        myPaint.setColor(getResources().getColor(R.color.color_eb6100));
        canvas.drawRect((float) (systemFile * getWidth())
                , 0, (float) ((systemFile + pictureFile) * getWidth()), getHeight(), myPaint);
        myPaint.setColor(getResources().getColor(R.color.color_00a4ff));
        canvas.drawRect((float) ((systemFile + pictureFile) * getWidth())
                , 0, (float) ((systemFile + pictureFile + rawFile) * getWidth()), getHeight(), myPaint);
        myPaint.setColor(getResources().getColor(R.color.color_434343));
        canvas.drawRect((float) ((systemFile + pictureFile + rawFile) * getWidth()),
                0, (float) ((systemFile + pictureFile + rawFile + temporaryFile) * getWidth()), getHeight(), myPaint);
    }

    /**
     * 设置内存百分比
     *
     * @param percents 各区域占的百分比数组
     */
    public void setPercent(double[] percents) {
        systemFile = percents[0];
        pictureFile = percents[1];
        rawFile = percents[2];
        temporaryFile = percents[3];
        invalidate();
    }
}
