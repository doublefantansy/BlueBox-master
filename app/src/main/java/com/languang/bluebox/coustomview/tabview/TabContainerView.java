package com.languang.bluebox.coustomview.tabview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.languang.bluebox.R;
import com.languang.bluebox.coustomview.CustomViewPager;

/**
 * @author mrj
 * @date 17/4/26
 */
public class TabContainerView extends RelativeLayout {
    /**
     * 底部TabLayout
     */
    private TabHost tabHost;
    /**
     * 中间ViewPager
     */
    private CustomViewPager contentViewPager;
    TabViewPagerAdapter adapter;
    /**
     * 文本属性
     */
    private int textSize;
    private int textColor;
    private int selectedTextColor;
    private int drawablePadding;
    /**
     * 图标属性
     */
    private int iconHeight;
    private int iconWidth;
    /**
     * 分割线
     */
    BaseAdapter baseAdapter;
    private int divideLineColor;
    private int divideLineHeight;
    private OnTabSelectedListener onTabSelectedListener;

    public TabContainerView(Context context) {
        super(context);
    }

    public TabContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TabContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TabContainerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initStyle(context, attrs);
        initTabHost(context);
        initDivideLine(context);
        initViewPager(context);
        tabHost.setContentViewPager(contentViewPager);
    }

    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabContainerViewStyle);
        textColor = typedArray.getColor(R.styleable.TabContainerViewStyle_tabTextColor, getResources().getColor(R.color.color_666666));
        selectedTextColor = typedArray.getColor(R.styleable.TabContainerViewStyle_selectedTextColor, getResources().getColor(R.color.mainColor));
        textSize = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_tabTextSize, 0);
        drawablePadding = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_drawablePadding, 0);
        iconHeight = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_iconHeight, 0);
        iconWidth = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_iconWidth, 0);
        divideLineColor = typedArray.getColor(R.styleable.TabContainerViewStyle_divideLineColor, Color.BLACK);
        divideLineHeight = typedArray.getDimensionPixelSize(R.styleable.TabContainerViewStyle_divideLineHeight, Display.dip2px(context, 1));
        typedArray.recycle();
    }

    private void initTabHost(Context context) {
        tabHost = new TabHost(context);
        addView(tabHost.getRootView());
    }

    private void initDivideLine(Context context) {
        View divideLine = new View(context);
        divideLine.setId(R.id.divide_tab);
        divideLine.setBackgroundColor(divideLineColor);
        LayoutParams lineLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, divideLineHeight);
        lineLp.addRule(RelativeLayout.ABOVE, R.id.linearlayout_tab);
        divideLine.setLayoutParams(lineLp);
        addView(divideLine);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private void initViewPager(final Context context) {
        contentViewPager = new CustomViewPager(context);
        contentViewPager.setOffscreenPageLimit(3);
        LayoutParams contentVpLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentVpLp.addRule(RelativeLayout.ABOVE, R.id.divide_tab);
        contentViewPager.setLayoutParams(contentVpLp);
        contentViewPager.setId(R.id.viewpager_tab);
        contentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabHost.onChangeTabHostStatus(position);
                Tab selectedTab = tabHost.getTabForIndex(position);
                if (onTabSelectedListener != null && selectedTab != null) {
                    onTabSelectedListener.onTabSelected(selectedTab);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        addView(contentViewPager);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        setAdapter(baseAdapter, 0);
    }

    public void setAdapter(BaseAdapter baseAdapter, int index) {
        if (baseAdapter == null) return;
        this.baseAdapter = baseAdapter;
        tabHost.addTabs(baseAdapter, textSize, textColor, selectedTextColor, drawablePadding, iconWidth, iconHeight);
        adapter = new TabViewPagerAdapter(baseAdapter.getFragmentManager(), baseAdapter.getFragmentArray());
        contentViewPager.setAdapter(adapter);
        setCurrentItem(index);
    }

    public void setCurrentItem(int index) {
        tabHost.onChangeTabHostStatus(index);
        contentViewPager.setCurrentItem(index);
    }

    public void setTabText(int index) {
        tabHost.getTabForIndex(index)
                .setTabText("zhende");
    }

    public void notmove(boolean t) {
        tabHost.notmove(t);
    }

    public void setTabTextGone(boolean t) {
        Log.d("cctag", t + "");
        tabHost.setGone(t);
//        tabHost.getTabForIndex(index).setTabText("zhende");
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }
}
