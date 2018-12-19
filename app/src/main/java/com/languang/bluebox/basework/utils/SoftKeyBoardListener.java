package com.languang.bluebox.basework.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import com.languang.bluebox.coustomview.CustomEditText;

/**
 * 时间分类图片
 *
 * @author 49829
 * @date 2018/3/29
 */
public class SoftKeyBoardListener {
    /**
     * activity的根视图
     */
    private View rootView;
    /**
     * 纪录根视图的显示高度
     */
    int rootViewVisibleHeight;
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

    public SoftKeyBoardListener(Activity activity, final CustomEditText editText) {
        //获取activity的根视图
        rootView = activity.getWindow().getDecorView();

        //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取当前根视图在屏幕上显示的大小
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);

                int visibleHeight = r.height();
                System.out.println("" + visibleHeight);
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
                if (rootViewVisibleHeight == visibleHeight) {
                    return;
                }

                //根视图显示高度变小超过200，可以看作软键盘显示了
                if (rootViewVisibleHeight - visibleHeight > 200) {
//                    if (onSoftKeyBoardChangeListener != null) {
//                        onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
//                    }
                    if (null != editText) {
                        editText.setCursorVisible(true);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                //根视图显示高度变大超过200，可以看作软键盘隐藏了
                if (visibleHeight - rootViewVisibleHeight > 200) {
//                    if (onSoftKeyBoardChangeListener != null) {
//                        onSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
//                    }
                    if (null != editText) {
                        editText.setCursorVisible(false);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

            }
        });
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public interface OnSoftKeyBoardChangeListener {
        /**
         * 软键盘显示
         *
         * @param height 高度
         */
        void keyBoardShow(int height);

        /**
         * 软键盘隐藏
         *
         * @param height 高度
         */
        void keyBoardHide(int height);
    }

    /**
     * 监听软键盘的 弹出与收缩
     *
     * @param activity 活动
     * @param editText 对应的对话框
     */
    public static void setListener(Activity activity, CustomEditText editText) {
        SoftKeyBoardListener softKeyBoardListener = new SoftKeyBoardListener(activity, editText);
//        softKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
    }
}