package com.languang.bluebox;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.CropActivity;
import com.languang.bluebox.activity.VoiceBean;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.coustomview.tabview.MainViewAdapter;
import com.languang.bluebox.coustomview.tabview.TabContainerView;
import com.languang.bluebox.entity.AccessPoint;
import com.languang.bluebox.entity.CountImgInfo;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.fragment.LightDiskFragment;
import com.languang.bluebox.fragment.facility.FacilityFragment;
import com.languang.bluebox.fragment.mapstorage.MapStorageFragment;
import com.languang.bluebox.fragment.propertysheet.PropertySheetFragment;
import com.languang.bluebox.receiver.ConnectedStateReceiver;
import com.languang.bluebox.receiver.ScanResultReceiver;
import com.languang.bluebox.receiver.WiFiStateReceiver;
import com.languang.bluebox.utils.WiFiHandler;
import com.languang.bluebox.utils.wifi.Courier;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页
 *
 * @author 49829
 */
public class MainActivity extends BaseFragmentActivity implements FAInterface {
    @BindView(R.id.tab_container)
    TabContainerView tabContainer;
    @BindView(R.id.ss)
    LinearLayout ss;
    @BindView(R.id.ss1)
    LinearLayout ss1;
    public static int count;
    public static int count2;
    protected MainViewAdapter mainViewAdapter;
    @BindView(R.id.bottom_tv)
    TextView bottomTv;
    @BindView(R.id.biaozhus)
    LinearLayout biaozhus;
    @BindView(R.id.del)
    LinearLayout del;
    @BindView(R.id.share)
    LinearLayout share;
    @BindView(R.id.voice)
    LinearLayout voice;
    @BindView(R.id.meihua)
    LinearLayout meihua;
    @BindView(R.id.ss2)
    LinearLayout ss2;
    @BindView(R.id.ani)
    ImageView anim;
    @BindView(R.id.ani1)
    ImageView anim1;
    @BindView(R.id.tianjiadaochu)
    LinearLayout out;
    private WiFiStateReceiver wiFiStateReceiver;
    private ScanResultReceiver scanResultReceiver;
    private ConnectedStateReceiver connectedStateReceiver;
    private WiFiHandler wiFiHandler;
    private List<AccessPoint> currentAps;
    private HashMap<String, Integer> configuredAP;
    PictureStoreageFragment fragment1;
    MapStorageFragment fragment2;
    LightDiskFragment fragment3;
    PropertySheetFragment fragment4;
    FacilityFragment fragment5;
    List<Fragment> fragments = new ArrayList<>();
    List<ImgEntity> chose = new ArrayList<>();
    List<ImgEntity> mapChose = new ArrayList<>();
    List<ImgListEntity> mapChose1;
    Thread thread;
    PopupWindow popupWindow;
    AnimationDrawable drawable;
    WindowManager.LayoutParams ll;
    ArrayList<Uri> imageUris = new ArrayList<Uri>();
    TextView content;
    int count3 = 0;
    int count1 = 0;
    private In in = new In() {
        @Override
        public void click() {
        }
    };
    private boolean isFirst = true;
    private Bitmap bitmap;

    public List<ImgEntity> getMapChose() {
        return mapChose;
    }

    public void setMapChose(List<ImgEntity> mapChose) {
        this.mapChose = mapChose;
    }

    public void clear() {
        chose.clear();
        ss.setVisibility(View.INVISIBLE);
        tabContainer.setTabTextGone(false);
    }

    public void clear1() {
        mapChose.clear();
        ss.setVisibility(View.INVISIBLE);
        tabContainer.setTabTextGone(false);
    }

    //    public void clea1r() {
//        chose.clear();
//        ss.setVisibility(View.INVISIBLE);
//        tabContainer.setTabTextGone(false);
//    }
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    public void setIn(In in) {
        this.in = in;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mapChose.clear();
//        fragment2.fragment1.adapter.adapter.clear();
    }

    @Override
    protected void initView() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(
                        getResources()
                                .getColor(R.color.myBlue));
                //底部导航栏
                window.setNavigationBarColor(getResources()
                        .getColor(R.color.myBlue));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count3 = 0;
                imageUris.clear();
                for (ImgEntity imgEntity : mapChose) {
                    Glide.with(MainActivity.this)
                            .asBitmap()
                            .load(TimeUtils.getWlanIp() + "/public/" + imgEntity
                                    .getSrcpath() + imgEntity
                                    .getSrcname())
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                    bitmap = resource;
                                    count3++;
                                    File file = new File(CropActivity.saveBitmap(MainActivity.this, bitmap));
                                    imageUris.add(Uri.fromFile(file));
                                    if (count3 == mapChose.size()) {
                                        Intent shareIntent = new Intent();
                                        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                                        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                                        shareIntent.setType("image/*");
                                        startActivity(Intent.createChooser(shareIntent, "分享图片"));
                                    }
                                }
                            });
                }
            }
        });
        drawable = (AnimationDrawable) anim.getBackground();
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapChose.size() == 1) {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    VoiceBean path = mapChose.get(0)
                            .getVoice();
                    if (path != null) {
                        try {
                            anim1.setVisibility(View.GONE);
                            anim.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "开始播放", Toast.LENGTH_SHORT)
                                    .show();
                            mediaPlayer.setDataSource(TimeUtils.getWlanIp() + "/public/" + path.getVcpath()
                                    + path
                                    .getVcname());
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                        drawable.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                drawable.stop();
                                anim1.setVisibility(View.VISIBLE);
                                anim.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "播放完毕", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "该图片没有相应的音频", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "只能播放一个音频", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        meihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CropActivity.class));
            }
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        OkHttpUtils.getInstance()
                                .okPost(MainActivity.this, ApiConstant.CLOUD_PULL_MSG, null, new OkHttpCallBack() {
                                    @Override
                                    public void onSucceed(String requestUrl, String response) {
                                        LogFiles.d("cctag", requestUrl + ":" + response);
                                        final ResponseMessage<MessageBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<MessageBean>>() {
                                        }.getType());
                                        if (responseMessage.getData()
                                                .getStatus()
                                                .equals("9999")) {
                                            if (Integer.valueOf(responseMessage.getData().count) > 0) {
                                                content.setText(responseMessage.getData().msg);
                                                if (!popupWindow.isShowing()) {
                                                    ll.alpha = 0.5f;
                                                    MainActivity.this.getWindow()
                                                            .setAttributes(ll);
                                                    popupWindow.showAtLocation(MainActivity.this.getWindow()
                                                            .getDecorView(), Gravity.CENTER, 0, 0);
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailed() {
                                    }
                                });
                    }
                };
                timer.schedule(timerTask,
                        0,//延迟1秒执行
                        10000);
            }
        });
//        thread.start();
        popupWindow = new PopupWindow();
        View view = getLayoutInflater().inflate(R.layout.messageview, null);
        Button submit = view.findViewById(R.id.submit);
        Button cancel = view.findViewById(R.id.cancel);
        content = view.findViewById(R.id.content);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                OkHttpUtils.getInstance()
//                        .okPost(MainActivity.this, );
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                ll.alpha = 1f;
                MainActivity.this.getWindow()
                        .setAttributes(ll);
            }
        });
        popupWindow.setContentView(view);
        popupWindow.setHeight(400);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(800);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        ll = getWindow().getAttributes();
        bottomTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        fragment1 = new PictureStoreageFragment(this);
        fragment2 = new MapStorageFragment();
        fragment3 = new LightDiskFragment();
        fragment4 = new PropertySheetFragment();
        fragment5 = new FacilityFragment(getIntent().getIntExtra("p", 11));
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("cctag", mapChose.size() + "");
                Intent intent = new Intent(MainActivity.this, DaoChuActivity.class);
                intent.putExtra("spe", new Gson().toJson(mapChose));
                startActivity(intent);
                mapChose.clear();
            }
        });
        mainViewAdapter = new MainViewAdapter(getSupportFragmentManager(),
                fragments);
        tabContainer.setAdapter(mainViewAdapter);
        biaozhus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BiaoZhuActivity.class);
                intent.putExtra("json", new Gson().toJson(chose));
                startActivity(intent);
            }
//            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ccnb", chose.size() + "");
                for (final ImgEntity imgEntity : chose) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("file", imgEntity.getUuid());
                    OkHttpUtils.getInstance()
                            .okPost(MainActivity.this, TimeUtils.getWlanIp() + "/deletefiles", map, new OkHttpCallBack() {
                                @Override
                                public void onSucceed(String requestUrl, String response) {
                                    count1++;
                                    Log.d("ccnbsss", count1 + "");
                                    if (count1 == chose.size()) {
                                        Log.d("ccnbsss", count1 + "end");
                                        count1 = 0;
                                        fragment1.refresh();
                                        chose.clear();
                                        fragment1.title.setText("宝盒新增文件");
                                    }
                                }

                                @Override
                                public void onFailed() {
                                    Log.d("ccnb", "fa");
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void initData() {
//        wiFiHandler = WiFiHandler.instance();
//        wiFiHandler.startScan();
        currentAps = new ArrayList<>();
        configuredAP = new HashMap<>();
        getCountImg();
    }

    private void getCountImg() {
        OkHttpUtils.getInstance()
                .okPost(this, TimeUtils.getWlanIp() + "/countimg", null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        Log.d("cccccccccccc", response);
                        ResponseMessage<CountImgInfo> responseMessage = new Gson().fromJson(response,
                                new TypeToken<ResponseMessage<CountImgInfo>>() {
                                }.getType());
                        if (Constant.SUCCEED_CODE == responseMessage.getRet()) {
                            count = responseMessage.getData()
                                    .getTagimg()
                                    .getImageCount();
                            count2 = responseMessage.getData()
                                    .getTagimg()
                                    .getVideoCount();
                            bottomTv.setText("新标注" + responseMessage.getData()
                                    .getTagimg()
                                    .getImageCount() + "张照片，"
                                    + responseMessage.getData()
                                    .getTagimg()
                                    .getVideoCount() + "个视频未光盘回档");
                        }
                    }

                    @Override
                    public void onFailed() {
                        Log.d("ccnb", "lsy1");
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterReceiver();
    }

    /**
     * 注册广播接收器
     */
    private void initReceiver() {
        wiFiStateReceiver = new WiFiStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wiFiStateReceiver, filter);
        scanResultReceiver = new ScanResultReceiver();
        IntentFilter scanFilter = new IntentFilter();
        scanFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(scanResultReceiver, scanFilter);
        connectedStateReceiver = new ConnectedStateReceiver();
        IntentFilter connectFilter = new IntentFilter();
        connectFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        connectFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(connectedStateReceiver, connectFilter);
    }

    /**
     * 注销广播
     */
    private void unRegisterReceiver() {
        unregisterReceiver(wiFiStateReceiver);
        unregisterReceiver(scanResultReceiver);
        unregisterReceiver(connectedStateReceiver);
        Courier.recycle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //    public void setGoneTag(boolean t, List<ImgListEntity> imgEntities1) {
//        Log.d("ccnbccnb", imgEntities1.size() + "");
//        mapChose1 = imgEntities1;
//        ss1.setVisibility(View.GONE);
//        ss2.setVisibility(View.VISIBLE);
//        if (!t) {
//            ss.setVisibility(View.VISIBLE);
//            tabContainer.setTabTextGone(true);
//        } else {
//            ss.setVisibility(View.INVISIBLE);
//            tabContainer.setTabTextGone(false);
//        }
//    }
    public void setGone(boolean t, List<ImgEntity> imgEntities1) {
        Set<ImgEntity> set = new LinkedHashSet<ImgEntity>();
        set.addAll(imgEntities1);
        List<ImgEntity> list2 = new ArrayList<ImgEntity>();
        list2.addAll(set);
        Log.d("ccnbccnb", list2.size() + "");
        mapChose = list2;
        ss1.setVisibility(View.GONE);
        ss2.setVisibility(View.VISIBLE);
        if (!t) {
            ss.setVisibility(View.VISIBLE);
            tabContainer.setTabTextGone(true);
        } else {
            ss.setVisibility(View.INVISIBLE);
            tabContainer.setTabTextGone(false);
        }
    }

    public void setGone2(boolean t) {
        ss1.setVisibility(View.GONE);
        ss2.setVisibility(View.VISIBLE);
        if (t) {
            ss.setVisibility(View.VISIBLE);
            tabContainer.setTabTextGone(true);
        } else {
            ss.setVisibility(View.INVISIBLE);
            tabContainer.setTabTextGone(false);
        }
    }

    @Override
    public void click(boolean t, boolean isadd, ImgEntity imgEntity) {
        Log.d("cctag", chose.size() + "");
        ss1.setVisibility(View.VISIBLE);
        ss2.setVisibility(View.GONE);
        if (t) {
            ss.setVisibility(View.VISIBLE);
            tabContainer.setTabTextGone(true);
            if (isadd) {
                chose.add(imgEntity);
            } else {
                chose.remove(imgEntity);
            }
        } else {
            ss.setVisibility(View.INVISIBLE);
            tabContainer.setTabTextGone(false);
        }
//        tabContainer.setTabTextGone(t);
//        tabContainer.setVisibility(View.INVISIBLE);
    }
}

