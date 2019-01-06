package com.languang.bluebox;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class YourGridView extends GridView {
    private boolean isOnMeasure;

    public boolean isOnMeasure() {
        return isOnMeasure;
    }

    public void setOnMeasure(boolean onMeasure) {
        isOnMeasure = onMeasure;
    }

    public YourGridView(Context context) {
        super(context);
    }

    public YourGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YourGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        isOnMeasure = true;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        isOnMeasure = false;
        super.onLayout(changed, l, t, r, b);
    }
}
