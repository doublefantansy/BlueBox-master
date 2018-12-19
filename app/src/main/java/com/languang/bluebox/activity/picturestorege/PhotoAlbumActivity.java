package com.languang.bluebox.activity.picturestorege;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.languang.bluebox.R;
import com.languang.bluebox.adapter.picturestorege.PhotoAlbumAdapter;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.utils.image.ImageBucket;
import com.languang.bluebox.utils.image.ImageProvider;
import com.luck.easyrecyclerview.EasyRecyclerView;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手机相册
 *
 * @author 49829
 * @date 2018/4/16
 */

public class PhotoAlbumActivity extends BaseFragmentActivity {


    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;

    private PhotoAlbumAdapter adapter;
    private List<ImageBucket> list = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_photo_album;
    }

    @Override
    protected void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(manager);
        adapter = new PhotoAlbumAdapter(this);
        adapter.clear();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        loadImage();

    }

    private void loadImage() {
        ImageProvider imageProvider = ImageProvider.getInstance();
        imageProvider.loadImageBucketList(this, new ImageProvider.OnLoadedBucketListListener() {
            @Override
            public void onLoaded(final List<ImageBucket> list) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.item_title_left_tv, R.id.item_title_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_title_left_tv:
                dialogUtil.showDialog("上传中...");
                OkHttpUtils.getInstance().okPost(this, ApiConstant.BOX_UPLOAD, null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        dialogUtil.dismissDialog();
                        ToastUtilsBase.showToastCenter(getBaseContext(), "上传完成");
                    }

                    @Override
                    public void onFailed() {

                    }
                });
                break;
            case R.id.item_title_right_tv:
                finish();
                break;
        }
    }
}
