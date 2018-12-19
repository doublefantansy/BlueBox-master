package com.languang.bluebox.fragment.mapstorage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 49829 on 2018/4/18.
 */

@SuppressLint("ValidFragment")
public class ImageFragment extends BaseFragment {

    @BindView(R.id.image)
    ImageView image;
    Unbinder unbinder;
    private Bitmap bitmap;

    public ImageFragment(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_image;
    }

    @Override
    protected void initView(View view) {
        image.setImageBitmap(bitmap);
    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
