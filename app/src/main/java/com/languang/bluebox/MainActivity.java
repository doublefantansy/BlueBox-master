package com.languang.bluebox;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.coustomview.tabview.MainViewAdapter;
import com.languang.bluebox.coustomview.tabview.TabContainerView;
import com.languang.bluebox.entity.AccessPoint;
import com.languang.bluebox.entity.CountImgInfo;
import com.languang.bluebox.entity.ImgEntity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static int count;
    public static int count2;
    protected MainViewAdapter mainViewAdapter;
    @BindView(R.id.bottom_tv)
    TextView bottomTv;
    @BindView(R.id.biaozhus)
    TextView biaozhus;
    @BindView(R.id.del)
    TextView del;
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
    private In in = new In() {
        @Override
        public void click() {
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    public void setIn(In in) {
        this.in = in;
    }

    @Override
    protected void initView() {
        fragment1 = new PictureStoreageFragment(this);
        fragment2 = new MapStorageFragment();
        fragment3 = new LightDiskFragment();
        fragment4 = new PropertySheetFragment();
        fragment5 = new FacilityFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        mainViewAdapter = new MainViewAdapter(getSupportFragmentManager(),
                fragments);
        tabContainer.setAdapter(mainViewAdapter);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                Log.d("ccnb", chose.size() + "");
//                map.put("file", );
//                OkHttpUtils.getInstance()
//                        .okPost(this, ApiConstant.BOX_DELETE_FILES, );
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
                .okPost(this, ApiConstant.BOX_COUNT_IMG, null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        Log.d("ccnb", "lsy2");
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

    @Override
    public void click(boolean t, boolean isadd, ImgEntity imgEntity) {
        if (t) {
            ss.setVisibility(View.VISIBLE);
            tabContainer.setTabTextGone(true);
        } else {
            ss.setVisibility(View.INVISIBLE);
            tabContainer.setTabTextGone(false);
//            if (isadd) {
//                chose.add(imgEntity);
//            } else {
//                chose.remove(imgEntity);
//            }
        }
//        tabContainer.setTabTextGone(t);
//        tabContainer.setVisibility(View.INVISIBLE);
    }
}
