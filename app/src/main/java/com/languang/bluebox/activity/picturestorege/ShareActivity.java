package com.languang.bluebox.activity.picturestorege;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.adapter.BaseDataAdapter;
import com.languang.bluebox.adapter.picturestorege.ShareHorizontalAdapter;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.coustomview.HorizontalListView;
import com.languang.bluebox.coustomview.MyScrollView;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.languang.bluebox.fragment.mapstorage.ImageFragment;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ScreenUtilBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 49829
 * @date 2018/4/18
 */
public class ShareActivity extends BaseFragmentActivity implements OkHttpCallBack {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.list_view)
    HorizontalListView listView;
    @BindView(R.id.picture_state)
    TextView pictureState;
    @BindView(R.id.ly_1)
    LinearLayout lyView;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.sv_1)
    MyScrollView sv;
    private List<String> lists = new ArrayList<String>();
    /**
     * // true:显示详情 false 反之
     */
    private boolean isOpen = false;
    private int screenHeight = 0;
    /**
     * // 总的手指滑动距离 Y轴
     */
    private int offsetSumY = 0;
    /**
     * // 总的手指滑动距离 X轴
     */
    private int offsetSumX = 0;
    private ShareHorizontalAdapter shareHorizontalAdapter;
    private List<String> list = new ArrayList<>();
    int choosePosition = 0;
    private List<Fragment> imageFragments = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private BaseDataAdapter adapter;
    private Point point = new Point();
    Bitmap bitmap;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_share;
    }

    @Override
    protected void initView() {
        setTitle("图片");
        setRightText("编辑");
        setRightOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, CompilePictureActivity.class));
            }
        });
        ImgListEntity url = new Gson().fromJson(getIntent().getStringExtra("ima"), ImgListEntity.class);
        for (ImgEntity imgEntity : url.getImgEntityList()) {
            Glide.with(this)
                    .asBitmap()
                    .load(TimeUtils.getWlanIp() + "/public/" + imgEntity.getSrcpath() + imgEntity.getSrcname())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            bitmap = resource;
                            if (!bitmap.equals("")) {
                                imageFragments.add(new ImageFragment(bitmap));
                            }
                        }
                    });
        }
//        imageFragments.add(new ImageFragment(BitmapFactory.decodeResource(getResources(), R.mipmap.p1)));
//        imageFragments.add(new ImageFragment(BitmapFactory.decodeResource(getResources(), R.mipmap.p2)));
//        imageFragments.add(new ImageFragment(BitmapFactory.decodeResource(getResources(), R.mipmap.p4)));
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        // 设置滑动层为屏幕高度
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) lyView.getLayoutParams();
        screenHeight = ScreenUtilBase.getScreenHeight(this) - dip2px(this, 89) - getStatusBarHeight();
        lp.height = screenHeight;
        lyView.setLayoutParams(lp);
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) listview.getLayoutParams();
        lp2.height = screenHeight - dip2px(this, 150)
                - getStatusBarHeight();
        listview.setLayoutParams(lp2);
        for (int i = 0; i < 7; i++) {
            lists.add("测试");
        }
        adapter = new BaseDataAdapter(this, lists);
        listview.setAdapter(adapter);
        sv.smoothScrollTo(0, 0);
        isOpen = false;
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        shareHorizontalAdapter = new ShareHorizontalAdapter(mContext, list);
        listView.setAdapter(shareHorizontalAdapter);
        initScroll();
        initListOnclick();
        initOnTouch();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                shareHorizontalAdapter.selectImage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 添加滚动监听事件
     */
    private void initScroll() {
        sv.setOnScrollChangedListeneer(new MyScrollView.OnScrollChangedListeneer() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (isOpen) {
                    listView.setVisibility(View.GONE);
                    pictureState.setVisibility(View.GONE);
                    date.setVisibility(View.VISIBLE);
                } else {
                    listView.setVisibility(View.VISIBLE);
                    pictureState.setVisibility(View.VISIBLE);
                    date.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 图片列表点击事件
     */
    private void initListOnclick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosePosition = position;
                switch (position) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                }
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.share, R.id.add_export, R.id.speak, R.id.privacy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share:
                break;
            case R.id.add_export:
                String[] files = new String[2];
                Map<String, Object> params = new HashMap<>();
                params.put("files", files);
                OkHttpUtils.getInstance()
                        .okPost(this, ApiConstant.BOX_FILE_OUT, params, this);
                break;
            case R.id.speak:
                break;
            case R.id.privacy:
                break;
        }
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
    }

    @Override
    public void onFailed() {
    }

    /**
     * viewpager适配器
     */
    class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return imageFragments.get(position);
        }

        @Override
        public int getCount() {
            return imageFragments.size();
        }
    }

    /**
     * dip转换为px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 滑动监听
     */
    private void initOnTouch() {
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int action = event.getAction();
                int offsety = 0;
                int offsetx = 0;
                int y = 0;
                int x = 0;
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        point.y = (int) event.getRawY();
                        point.x = (int) event.getRawX();
                        offsetSumY = 0;
                        offsetSumX = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        y = (int) event.getRawY();
                        x = (int) event.getRawX();
                        offsety = y - point.y;
                        offsetx = x - point.x;
                        offsetSumX += offsetx;
                        offsetSumY += offsety;
                        point.y = (int) event.getRawY();
                        sv.scrollBy(0, -offsety);
                        break;
                    case MotionEvent.ACTION_UP:
                        // offsetSumY大于0时是往下拉，只有当显示详情页是下拉才有效果，所以这里先判断isOpen的值。
                        if (offsetSumY > 0) {
                            Log.d(TAG, "onTouch: viewpager 30Y===" + offsetSumY);
                            if (isOpen) {
                                if (offsetSumY > 150) {
                                    sv.smoothScrollTo(0, 0);
                                    isOpen = false;
                                } else {
                                    sv.smoothScrollTo(0, screenHeight);
                                    isOpen = true;
                                }
                            } else {
                                sv.smoothScrollTo(0, 0);
                                isOpen = false;
                            }
                            if (offsetSumX > 150) {
                                if (viewPager.getCurrentItem() > 0) {
                                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                                }
                            } else if (offsetSumX < -150) {
                                if (viewPager.getCurrentItem() < 3) {
                                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                                }
                            }
                        } else if (offsetSumY < 0) {
                            // offsetSumY小于0时是往上拉，只有当隐藏详情页是下拉才有效果，所以这里先判断isOpen的值。
                            Log.d(TAG, "onTouch: viewpager -30Y===" + offsetSumY);
                            if (!isOpen) {
                                if (offsetSumY < -150) {
                                    sv.smoothScrollTo(
                                            0,
                                            screenHeight
                                                    - dip2px(mContext, 150));
                                    isOpen = true;
                                } else {
                                    sv.smoothScrollTo(0, 0);
                                    isOpen = false;
                                }
                            } else {
                                sv.smoothScrollTo(
                                        0,
                                        screenHeight
                                                - dip2px(mContext, 150));
                                isOpen = true;
                            }
                            if (offsetSumX > 150) {
                                if (viewPager.getCurrentItem() > 0) {
                                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                                }
                            } else if (offsetSumX < -150) {
                                if (viewPager.getCurrentItem() < 3) {
                                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
