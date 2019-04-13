package com.languang.bluebox.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.MainActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.Device;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.Test;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    int jinduzhi = 0;
//    private ILightDisk lightDiskModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_light_disk;
    }

    MyThread thread;
//    MyThread1 thread1;

    @Override
    protected void initView(View view) {
        thread = new MyThread();
        Map<String, Object> params = new HashMap<>(1);
        params.put("type", "cdrom");
        OkHttpUtils.getInstance()
                .okPost(getActivity(), TimeUtils.getWlanIp() + "/waisheinfo", params, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        ResponseMessage<Device> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<Device>>() {
                        }.getType());
                        List list = new ArrayList();
                        list.add(1);
                        responseMessage.getData()
                                .setCdrom(list);
                        if (responseMessage.getData()
                                .getCdrom()
                                .size() == 0) {
                            nothing.setVisibility(View.VISIBLE);
                            guidang.setVisibility(View.GONE);
                            xinxi.setVisibility(View.GONE);
                            relativeLayout.setVisibility(View.VISIBLE);
                            display.setText("刻录等待中");
                            progressBar.setVisibility(View.GONE);
                            content.setText("新标注" + MainActivity.count + "张照片/" + MainActivity.count2 + "个视频未光盘归档，需要光盘控件20G");
                        } else {
                            display.setText("光盘刻录中");
                            xinxi.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailed() {
                    }
                });
        start_archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guidang.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
                OkHttpUtils.getInstance()
                        .okPost(getActivity(), TimeUtils.getWlanIp() + "/startguidang", null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                ResponseMessage<Test> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<Test>>() {
                                }.getType());
                                if (responseMessage.getData()
                                        .getState()
                                        .equals("1")) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    thread.start();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), responseMessage.getData()
                                            .getMsg(), Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }

                            @Override
                            public void onFailed() {
                                Log.d("ccnb", "失败");
                            }
                        });
            }
        });
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (jinduzhi != 101) {
                OkHttpUtils.getInstance()
                        .okPost(getActivity(), TimeUtils.getWlanIp() + "/guidang", null, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                ResponseMessage<GuiDangJinDu> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<GuiDangJinDu>>() {
                                }.getType());
                                final int jindu = Integer.valueOf(responseMessage.getData()
                                        .getJindu());
                                if (jindu > 0 & jindu < 101) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.setProgress(jindu);
                                        }
                                    });
                                    try {
                                        sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                } else if (jindu == 101) {
                                    jinduzhi = jindu;
                                    display.setText("刻录已完成");
                                } else {
                                    jinduzhi = 101;
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });
                                    Toast.makeText(getActivity(), responseMessage.getData()
                                            .getMsg(), Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
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
