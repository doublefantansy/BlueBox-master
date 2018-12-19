package com.languang.bluebox;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.languang.NewPicture;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ResponseMessage;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class PictureStoreageFragment extends BaseFragment implements CountInterface {
    @BindView(R.id.none)
    TextView none;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.choose)
    TextView choose;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.title)
    TextView title;
    static int count = 0;
    Map<String, Map<String, ImgEntity>> mapMap = new LinkedTreeMap<>();
    List list = new ArrayList();
    Myadapter adapter;
    CountInterface countInterface;
    LinearLayoutManager layoutManager;
    FAInterface faInterface;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_picture;
    }

    public PictureStoreageFragment(FAInterface faInterface) {
        this.faInterface = faInterface;
    }

    @Override
    protected void initView(View view) {
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.begin();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        count = 0;
    }

    void refresh() {
//        title.setText("宝盒新增文件");
        OkHttpUtils.getInstance()
                .okPost(getActivity(), ApiConstant.BOX_NEW_LIST, null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        ResponseMessage<NewPicture> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<NewPicture>>() {
                        }.getType());
                        if (responseMessage.getData()
                                .getFiles()
                                .size() > 0) {
                            none.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            adapter = new Myadapter(getActivity(), responseMessage.getData()
                                    .getFiles(), PictureStoreageFragment.this);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            none.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailed() {
                        Log.d("ccnb", "fail");
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
//        refresh();
    }

    @Override
    protected void initData() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowManager m = getActivity().getWindowManager();
                DisplayMetrics outMetrics = new DisplayMetrics();
                m.getDefaultDisplay()
                        .getMetrics(outMetrics);
                int width = (int) (outMetrics.widthPixels * 0.6);
                PopView picturePopupWindow = new PopView(getActivity(), width, new MyCallBack() {
                    @Override
                    public void callback() {
                        refresh();
                    }
                });
                picturePopupWindow.showAsDropDown(add);
            }
        });
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        OkHttpUtils.getInstance()
//                .okPost(getActivity(), ApiConstant.BOX_NEW_LIST, null, new OkHttpCallBack() {
//                    @Override
//                    public void onSucceed(String requestUrl, String response) {
//
//                        ResponseMessage<NewPicture> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<NewPicture>>() {
//                        }.getType());
//                        if (responseMessage.getData()
//                                .getFiles()
//                                .size() > 0) {
//                            none.setVisibility(View.GONE);
//                            recyclerView.setVisibility(View.VISIBLE);
//                            adapter = new Myadapter(getActivity(), responseMessage.getData()
//                                    .getFiles());
//                            recyclerView.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
//                        } else {
//                            recyclerView.setVisibility(View.GONE);
//                            none.setVisibility(View.VISIBLE);
//                        }
//                    }
//
//                    @Override
//                    public void onFailed() {
//                        Log.d("ccnb", "fail");
//                    }
//                });
        refresh();
    }

    @Override
    public void click(boolean isadd, int count, ImgEntity imgEntity) {
        if (count == 0) {
            title.setText("宝盒新增文件");
        } else {
            title.setText("已选择" + count + "张照片");
        }
        if (isadd) {
            faInterface.click(true, isadd, imgEntity);
        } else {
            if (count == 0) {
                faInterface.click(false, isadd, imgEntity);
            } else {
                faInterface.click(true, isadd, imgEntity);
            }
        }
    }
}
