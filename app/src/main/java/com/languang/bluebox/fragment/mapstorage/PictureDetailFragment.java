package com.languang.bluebox.fragment.mapstorage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 图片详情界面
 *
 * @author 49829
 * @date 2018/4/13
 */

public class PictureDetailFragment extends BaseFragment {
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.pic_tag)
    TextView picTag;
    @BindView(R.id.file_name)
    TextView fileName;
    @BindView(R.id.create_time)
    TextView createTime;
    @BindView(R.id.measure_size)
    TextView measureSize;
    @BindView(R.id.room_size)
    TextView roomSize;
    @BindView(R.id.format)
    TextView format;
    @BindView(R.id.path)
    TextView path;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture_detail;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    public void changeImg(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
