package com.languang.bluebox.popupwd;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.languang.bluebox.R;
import com.languang.bluebox.activity.picturestorege.PhotoAlbumActivity;
import com.languang.bluebox.activity.picturestorege.UsbActivity;
import com.languang.bluebox.basework.base.BasePopupWindow;
import com.mrj.framworklib.utils.ToastUtilsBase;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 图库页面添加图片下拉弹窗
 *
 * @author 49829
 * @date 2018/3/27
 */
public class AddPicturePopupWindow extends BasePopupWindow {
    private View contentView;
    private Context context;

    public AddPicturePopupWindow(Context context, int width) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup_add_picture, null);
        // 设置PopupWindow的View
        this.setContentView(contentView);
        // 设置PopupWindow弹出窗体的宽
//        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(width);
        // 设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置不允许点击其他地方关闭popupWindow
        this.setOutsideTouchable(false);
        //设置允许触摸popupWindow
        this.setTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000333333);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置PopupWindow弹出窗体动画效果
        // 设置PopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popuwindow_bottom_anim_style);
        //绑定ButterKnife插件
        ButterKnife.bind(this, contentView);
    }

    @OnClick({R.id.file_ll, R.id.usb_ll, R.id.album_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.file_ll:
                ToastUtilsBase.showToastCenter(context, "文件");
                break;
            case R.id.usb_ll:
                dismiss();
                context.startActivity(new Intent(context, UsbActivity.class));
                break;
            case R.id.album_ll:
                dismiss();
                context.startActivity(new Intent(context, PhotoAlbumActivity.class));
                break;

            default:
                break;
        }
    }
}
