package com.languang.bluebox.activity.picturestorege;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑图片
 *
 * @author 49829
 * @date 2018/4/23
 */

public class CompilePictureActivity extends BaseFragmentActivity {
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.tailor)
    ImageView tailor;
    @BindView(R.id.filter)
    ImageView filter;
    @BindView(R.id.contrast)
    ImageView contrast;
    @BindView(R.id.watermark)
    ImageView watermark;
    @BindView(R.id.complete)
    TextView complete;


    private List<View> viewIds = new ArrayList<>(6);


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_compile_picture;
    }

    @Override
    protected void initView() {
        viewIds.add(cancel);
        viewIds.add(tailor);
        viewIds.add(filter);
        viewIds.add(contrast);
        viewIds.add(watermark);
        viewIds.add(complete);
    }

    @Override
    protected void initData() {

    }
    
    @OnClick({R.id.cancel, R.id.tailor, R.id.filter, R.id.contrast, R.id.watermark, R.id.complete})
    public void onViewClicked(View view) {
        mutualButtons(view.getId());
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.tailor:

                break;
            case R.id.filter:

                break;
            case R.id.contrast:

                break;
            case R.id.watermark:

                break;
            case R.id.complete:

                break;
            default:
                break;
        }
    }

    /**
     * 配置底部按钮的互斥事件
     *
     * @param viewId 点击的viewId
     */
    private void mutualButtons(int viewId) {
        for (View view :
                viewIds) {
            if (viewId == view.getId()) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
    }
}