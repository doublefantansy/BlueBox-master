package com.languang.bluebox.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.MainActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.SplashActivity;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.Device;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.Test;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 光盘页面
 *
 * @author 49829
 * @date 2018/3/26
 */
public class LightDiskFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.progress_position)
    TextView progressPosition;
    @BindView(R.id.start_archive)
    TextView start_archive;
    @BindView(R.id.recording_rl)
    RelativeLayout relativeLayout;
    @BindView(R.id.statr)
    RelativeLayout guidang;
    @BindView(R.id.disk)
    RelativeLayout xinxi;
    @BindView(R.id.image)
    RelativeLayout nothing;
    @BindView(R.id.display)
    TextView display;
    @BindView(R.id.content)
    TextView content;
//    private ILightDisk lightDiskModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_light_disk;
    }

    MyThread thread;

    @Override
    protected void initView(View view) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("type", "cdrom");
        OkHttpUtils.getInstance()
                .okPost(getActivity(), TimeUtils.getWlanIp()+"/waisheinfo", params, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        ResponseMessage<Device> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<Device>>() {
                        }.getType());
                        if (responseMessage.getData().getCdrom().size()==0)
                        {
                            nothing.setVisibility(View.VISIBLE);
                            guidang.setVisibility(View.GONE);
                            xinxi.setVisibility(View.GONE);
                            relativeLayout.setVisibility(View.VISIBLE);
                            display.setText("刻录等待中");

                            content.setText("新标注"+ MainActivity.count+"张照片/"+MainActivity.count2+"个视频未光盘归档，需要光盘控件20G");
                        }
                    }

                    @Override
                    public void onFailed() {
                    }
                });
        start_archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils.getInstance()
                        .okPost(getActivity(), SplashActivity.wlanIp+"/startguidang", null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                ResponseMessage<Test> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<Test>>() {
                                }.getType());
                                if (responseMessage.getData()
                                        .getState()
                                        .equals("1")) {
//                                    Log.d("ccnb", "lsy");
                                    nothing.setVisibility(View.VISIBLE);
                                    guidang.setVisibility(View.GONE);
                                    xinxi.setVisibility(View.GONE);
                                }
//                                relativeLayout.setVisibility(View.VISIBLE);
//                                relativeLayout1.setVisibility(View.GONE);
//                                OkHttpUtils.getInstance()
//                                        .okPost(getActivity(), ApiConstant.BOX_GUIDANG, null, new OkHttpCallBack() {
//                                            @Override
//                                            public void onSucceed(String requestUrl, String response) {
//                                            }
//
//                                            @Override
//                                            public void onFailed() {
//                                            }
//                                        });
                            }

                            @Override
                            public void onFailed() {
                                Log.d("ccnb", "失败");
                            }
                        });
            }
        });
//        thread = new MyThread();
//        thread.start();
    }
//    @OnClick(R.id.start_archive)
//    public void onViewClicked() {
//        lightDiskModel.archiveProgress(this);
//    }
//    @Override
//    public void onSucceed(String requestUrl, String response) {
//        if (ApiConstant.BOX_START_GUIDANG.equals(requestUrl)) {
//            lightDiskModel.archiveProgress(this);
//        } else if (ApiConstant.BOX_GUIDANG.equals(requestUrl)) {
//
//        }
//    }
//    @Override
//    public void onFailed() {
//
//    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i <= Constant.TOTAL_PROGRESS; i++) {
                if (null == progressBar || null == progressPosition) {
                    return;
                }
                progressBar.setProgress(i);
                //在子线程中发送消息
                final int finalI = i;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressPosition.setText(finalI + "%");
                    }
                });
                if (i == 100) {
                    new MyThread().start();
                    this.interrupt();
                    thread = null;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
//        lightDiskModel = new LightDiskModel(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
