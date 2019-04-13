package com.languang.bluebox;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class LoadingPopView extends PopupWindow {
    PopupWindow popupWindow;
    Context context;
    ObjectAnimator objectAnimator;

    public LoadingPopView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        popupWindow = new PopupWindow();
        popupWindow.setWidth(300);
        popupWindow.setHeight(300);
        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.loading, null);
        ImageView imageView1 = view.findViewById(R.id.iamge);
        popupWindow.setContentView(view);
        objectAnimator = ObjectAnimator.ofFloat(imageView1, "rotation", 0, 360);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new LinearInterpolator());
    }

    public void show() {
        popupWindow.showAtLocation(((Activity) context).getWindow()
                .getDecorView(), Gravity.CENTER, 0, 0);
        objectAnimator.start();
    }

    public void dissmiss() {
        popupWindow.dismiss();
    }
}
