package com.languang.bluebox.adapter.picturestorege;

import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class NewImgEntity {
    private boolean isCheaked;
    private FrameLayout linearLayout;
    private ImageView imageView;
    private CheckBox gou;

    public FrameLayout getLinearLayout() {
        return linearLayout;
    }

    public boolean isCheaked() {
        return isCheaked;
    }

    public void setCheaked(boolean cheaked) {
        isCheaked = cheaked;
    }

    public void setLinearLayout(FrameLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public CheckBox getGou() {
        return gou;
    }

    public void setGou(CheckBox gou) {
        this.gou = gou;
    }
}
