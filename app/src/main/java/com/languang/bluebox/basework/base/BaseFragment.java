package com.languang.bluebox.basework.base;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrj.framworklib.view.CustomProgressDialog;

import com.languang.bluebox.basework.utils.ProgressDialogUtil;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基础碎片
 *
 * @author 49829
 * @date 2017/11/23
 */

public abstract class BaseFragment extends Fragment {

    protected String TAG="marj";

    private View contentView;
    private Unbinder unbinder;

    protected ProgressDialogUtil dialogUtil;

    private CustomProgressDialog dialog;


    /**
     * 所有子类必须实现，绑定Act视图
     *
     * @return 布局文件
     */
    protected abstract int getLayoutResId();

    /**
     * 所有子类必须实现，里面做页面初始化
     *
     * @param view 布局
     */
    protected abstract void initView(View view);

    /**
     * 所有子类必须实现，里面做数据方面等操作。
     */
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutResId() != 0) {
            contentView = inflater.inflate(getLayoutResId(), container, false);
            unbinder = ButterKnife.bind(this, contentView);
            initView(contentView);
            dialogUtil = new ProgressDialogUtil(getActivity(), dialog);
            return contentView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dialogUtil.dismissDialog();
    }

    /**
     * 判断软键盘是否弹出
     *
     * @return true 弹出 false 未弹出
     */
    protected boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom != 0;
    }
}
