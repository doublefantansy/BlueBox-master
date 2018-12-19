package com.languang.bluebox;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.languang.bluebox.basework.base.BasePopupWindow;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;

public class PopView extends BasePopupWindow {
    private View contentView;
    private Context context;
    MaterialDialog dialog;
    static  MyCallBack myCallBack;
    int count = 0;

   public static MyCallBack getCallBack()
    {
        return myCallBack;
    }
    public PopView(Context context, int width, MyCallBack myCallBack) {
        super(context);
        this.myCallBack = myCallBack;
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup_add_picture1, null);
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

    @OnClick({R.id.waishe, R.id.xiangce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.waishe:
                dismiss();
//                context.startActivity(new Intent(context, PhotoAlbumActivity.class));
                break;
            case R.id.xiangce:
                dismiss();
                RxGalleryFinal.with(context)
                        .image()
                        .multiple()
                        .maxSize(9)
                        .imageLoader(ImageLoaderType.UNIVERSAL)
                        .subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>() {
                            @Override
                            protected void onEvent(final ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
//                                dialog.show();
//                                for (MediaBean mediaBean : imageMultipleResultEvent.getResult()) {
                                Intent intent = new Intent(context, AddPicActivity.class);
                                intent.putExtra("spe", new Gson().toJson(imageMultipleResultEvent.getResult()));
                                context.startActivity(intent);
//                                }
//
                            }
//                            }
                        })
                        .openGallery();
                break;
            default:
                break;
        }
    }
}
