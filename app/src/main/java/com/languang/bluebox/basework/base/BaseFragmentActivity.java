package com.languang.bluebox.basework.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrj.framworklib.utils.AppManager;
import com.mrj.framworklib.utils.ScreenUtilBase;
import com.mrj.framworklib.view.CustomProgressDialog;

import java.util.List;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.costom.DefaultCode;
import com.languang.bluebox.basework.utils.HideInputUtils;
import com.languang.bluebox.basework.utils.ProgressDialogUtil;
import com.noober.background.BackgroundLibrary;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基础界面
 *
 * @author 49829
 * @date 2017/11/23
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {
    private Unbinder unbinder;
    private CustomProgressDialog dialog;

    protected Activity mContext;
    protected String TAG = "marj";
    //title返回
    @Nullable
    @BindView(R.id.item_title_back_layout)
    LinearLayout backLayout;
    //title标题
    @Nullable
    @BindView(R.id.item_title_name)
    TextView titleName;

    //titleSmall标题
    @Nullable
    @BindView(R.id.item_title_small_name)
    TextView titleSmallName;
    //title右边文字
    @Nullable
    @BindView(R.id.item_title_right_tv)
    TextView rightText;

    //title右边文字Layout
    @Nullable
    @BindView(R.id.item_title_right_layout)
    LinearLayout rightLayout;

    private BaseActivityReceiver receiver;

    protected ProgressDialogUtil dialogUtil;

    /**
     * 所有子类必须实现，绑定Act视图
     *
     * @return 布局文件
     */
    protected abstract int getLayoutResId();

    /**
     * 所有子类必须实现，里面做页面初始化
     */
    protected abstract void initView();

    /**
     * 所有子类必须实现，里面做数据方面等操作。
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            BackgroundLibrary.inject(this);
            super.onCreate(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(getLayoutResId());
        AppManager.getAppManager().addActivity(this);

        initBackClick();

    }

    /**
     * 返回按钮监听
     */
    public void initBackClick() {
        if (backLayout != null) {
            backLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    AppManager.getAppManager().finishActivity(BaseFragmentActivity.this);
                    finish();
                }
            });
        }
    }

    /**
     * 设置标题
     *
     * @param name 标题名
     */
    public void setTitle(String name) {
        if (titleName != null) {
            titleName.setText(name);
        }
    }

    /**
     * 隐藏大标题  显示小标题
     *
     * @param message 小标题显示内容
     */
    public void hideBigeTitleShowSmallTitle(String message) {
        titleName.setVisibility(View.GONE);
        titleSmallName.setText(message);
    }

    /**
     * 隐藏小标题  显示小大标题
     *
     * @param message 大标题显示内容
     */
    public void hideSmallTitleShowBigeTitle(String message) {
        titleName.setText(message);
        titleSmallName.setVisibility(View.GONE);
    }

    /**
     * 设置右边文字
     * 默认隐藏
     */
    public void setRightText(String name) {
        if (rightText != null) {
            rightText.setVisibility(View.VISIBLE);
            rightText.setText(name);
        }
    }


    /**
     * 设置右边文字点击事件
     *
     * @param onClickListener 点击监听
     */
    public void setRightOnclick(View.OnClickListener onClickListener) {
        rightLayout.setOnClickListener(onClickListener);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ScreenUtilBase.setStatusBarColor(this, DefaultCode.STATUS_BAR_COLOR);

        unbinder = ButterKnife.bind(this);
        mContext = this;
        dialogUtil = new ProgressDialogUtil(this, dialog);

        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (receiver == null) {
            receiver = new BaseActivityReceiver();
            IntentFilter filter = new IntentFilter();
            registerReceiver(receiver, filter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        dialogUtil.dismissDialog();
        unregisterReceiver(receiver);
        AppManager.getAppManager().finishActivity(this);

    }

    /**
     * 隐藏软键盘
     *
     * @param ev 点击事件
     * @return 是否操作
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // get current focus,Generally it is EditText
            View view = getCurrentFocus();
            if (HideInputUtils.isShouldHideSoftKeyBoard(view, ev)) {
                HideInputUtils.hideSoftKeyBoard(view.getWindowToken(), this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    class BaseActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 是否显示在前台
     *
     * @param context 活动
     * @return Boolean
     */
    public boolean isForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


//    public final void showNotification(Intent intent) {
//        if (intent.getStringExtra("msg").length() >= 20) {
//            View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_get_success, null);
//            timeTv = (TextView) view1.findViewById(R.id.dialog_get_success_tv);
//            tieleTv = (TextView) view1.findViewById(R.id.dialog_title);
//            timeTv.setText(intent.getStringExtra("msg"));
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//            tieleTv.setText(format.format(new Date()).substring(5, format.format(new Date()).length()) + " 系统公告");
//            dialogBtn = (Button) view1.findViewById(R.id.dialog_btn);
//
//            notificationDialog = new Dialog(this);
//            notificationDialog.setContentView(view1);
//            notificationDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//
//            dialogBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    notificationDialog.dismiss();
//                }
//            });
//            notificationDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
//            notificationDialog.show();
//
//            WindowManager m = getWindowManager();
//            DisplayMetrics outMetrics = new DisplayMetrics();
//            m.getDefaultDisplay().getMetrics(outMetrics);
//            WindowManager.LayoutParams p = notificationDialog.getWindow().getAttributes();  //获取对话框当前的参数值
////                    p.height = (int) (outMetrics.heightPixels * 0.6);   //高度设置为屏幕的0.5
//            p.width = (int) (outMetrics.widthPixels * 0.8);   //宽度设置为屏幕的0.8
//            notificationDialog.getWindow().setAttributes(p);     //设置生效
//        } else {
//
//        }
//    }

}
