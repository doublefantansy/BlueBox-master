package com.languang.bluebox.activity.property;

import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.DonwloadSaveImg;
import com.languang.bluebox.DownloadBean;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.coustomview.CustomPercentView;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.fragment.propertysheet.OutBean;
import com.languang.bluebox.model.PropertySheetModel;
import com.languang.bluebox.presenter.IPropertySheet;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出设备
 *
 * @author 49829
 * @date 2018/4/17
 */
public class ExportFacilityActivity extends BaseFragmentActivity implements OkHttpCallBack {
    private IPropertySheet propertySheet;
    List<OutBean.Nocd.ImgEntitysp> imgEntitysps;
    List<WaiseBean.Usb> list;
    CustomPercentView customPercentView;
    CustomPercentView customPercentView2;
    TextView sd1;
    TextView sd2;
    TextView room1;
    TextView room2;
    Button write1;
    Button write2;
    Button download;
    int count = 0;
    int discount = 0;
    TextView co;
    TextView sd_name;
    TextView room_reduce;
    double total;
    double avail;
    int c = 0;
    //    private MaterialDialog mSaveDialog;
    DownInteface downInteface;

    //    room_reduce_1
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_export_facility;
    }

    private void getSd() {
        // TODO Auto-generated method stub
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlacks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();
        long totalSize = blockSize * totalBlacks;
        long availSize = availableBlocks * blockSize;
        String totalStr = Formatter.formatFileSize(ExportFacilityActivity.this, totalSize);
        String availStr = Formatter.formatFileSize(ExportFacilityActivity.this, availSize);
        total = Double.valueOf(totalStr.split("G")[0].trim());
        avail = Double.valueOf(availStr.split("G")[0].trim());
//        room_reduce.setText("Sd卡的总的容量是" + totalStr + "\n" + "SD卡的可用容量是" + availStr);
    }

    @Override
    protected void initView() {
        setTitle("导出设备");
        imgEntitysps = new Gson().fromJson(getIntent().getStringExtra("spe"), new TypeToken<List<OutBean.Nocd.ImgEntitysp>>() {
        }.getType());
        sd_name = findViewById(R.id.sd_name);
        room_reduce = findViewById(R.id.room_reduce);
        getSd();
        discount = getIntent().getIntExtra("spe1", 0);
        sd1 = findViewById(R.id.sd_name_1);
        customPercentView = findViewById(R.id.custom_percent_view_1);
        customPercentView2 = findViewById(R.id.custom_percent_view_2);
        download = findViewById(R.id.export_all);
        co = findViewById(R.id.co);
        co.setText("已读取照片" + discount + "张、视频0段共计0.0G请选择相关设备导出");
        sd2 = findViewById(R.id.sd_name_2);
        room1 = findViewById(R.id.room_reduce_1);
        room2 = findViewById(R.id.room_reduce_2);
        write1 = findViewById(R.id.browse_1);
        write2 = findViewById(R.id.browse_2);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MaterialDialog mSaveDialog = new MaterialDialog.Builder(ExportFacilityActivity.this).title("图片正在下载中")
                        .progress(false, imgEntitysps.size(), true)
                        .cancelable(false)
                        .build();
                mSaveDialog.show();
                for (final OutBean.Nocd.ImgEntitysp imgEntitysp : imgEntitysps) {
                    final Map<String, Object> map = new HashMap<>();
                    map.put("files", imgEntitysp.getUuid());
                    OkHttpUtils.getInstance()
                            .okPost(ExportFacilityActivity.this, TimeUtils.getWlanIp() + "/outphone", map, new OkHttpCallBack() {
                                @Override
                                public void onSucceed(String requestUrl, String response) {
                                    Log.d("ccnb", response);
                                    ResponseMessage<DownloadBean> imgEntityspResponseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<DownloadBean>>() {
                                    }.getType());
                                    if (imgEntityspResponseMessage.getData()
                                            .getStatus()
                                            .equals("9999")) {
//                                        c++;
//                                        if (c == imgEntitysps.size()) {
//                                            mSaveDialog.dismiss();
//                                        }
                                        for (Map.Entry<String, DownloadBean.FilesBean> entry : imgEntityspResponseMessage.getData()
                                                .getFiles()
                                                .entrySet()) {
                                            DonwloadSaveImg.donwloadImg(ExportFacilityActivity.this, TimeUtils.getWlanIp() + "/public/" + entry.getValue()
                                                    .getSrcpath() + entry.getValue()
                                                    .getSrcname(), new DownInteface() {
                                                @Override
                                                public void click(int a) {
                                                    mSaveDialog.setProgress(a);
                                                    if (a == imgEntitysps.size()) {
                                                        mSaveDialog.dismiss();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }

                                @Override
                                public void onFailed() {
                                }
                            });
                }
            }
        });
        write1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (final OutBean.Nocd.ImgEntitysp imgEntitysp : imgEntitysps) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("usbname", list.get(0)
                            .getName());
                    map.put("files", imgEntitysp.getUuid());
                    OkHttpUtils.getInstance()
                            .okPost(ExportFacilityActivity.this, TimeUtils.getWlanIp() + "/outusb", map, new OkHttpCallBack() {
                                @Override
                                public void onSucceed(String requestUrl, String response) {
                                    count++;
                                    if (count == imgEntitysps.size()) {
                                        finish();
                                        Toast.makeText(ExportFacilityActivity.this, "迁出成功", Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }

                                @Override
                                public void onFailed() {
                                    Log.d("ccnbccnb", "sss");
                                }
                            });
                }
            }
        });
        write2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (final OutBean.Nocd.ImgEntitysp imgEntitysp : imgEntitysps) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("usbname", list.get(1)
                            .getName());
                    map.put("files", imgEntitysp.getUuid());
                    OkHttpUtils.getInstance()
                            .okPost(ExportFacilityActivity.this, TimeUtils.getWlanIp() + "/outusb", map, new OkHttpCallBack() {
                                @Override
                                public void onSucceed(String requestUrl, String response) {
                                    count++;
                                    if (count == imgEntitysps.size()) {
                                        finish();
                                        Toast.makeText(ExportFacilityActivity.this, "迁出成功", Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }

                                @Override
                                public void onFailed() {
                                    Log.d("ccnbccnb", "sss");
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void initData() {
        propertySheet = new PropertySheetModel(this);
        propertySheet.facilityList("usb", this);
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        Log.d("ccnbccnb", response);
        ResponseMessage<WaiseBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<WaiseBean>>() {
        }.getType());
        list = responseMessage.getData().usb;
        sd1.setText(list.get(0)
                .getName());
        sd2.setText(list.get(1)
                .getName());
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        room1.setText("已使用" + decimalFormat.format((double) (list.get(0)
                .getInfo() / 1024 / 1024 / 1024)) + "G/" + decimalFormat.format((double) (list.get(0)
                .getTotal() / 1024 / 1024 / 1024)) + "G");
        double[] doubles = new double[2];
        doubles[0] = list.get(0)
                .getInfo();
        doubles[1] = list.get(0)
                .getTotal();
        customPercentView.setPercent(doubles);
        room2.setText("已使用" + decimalFormat.format((double) (list.get(1)
                .getInfo() / 1024 / 1024 / 1024)) + "G/" + decimalFormat.format((double) (list.get(1)
                .getTotal() / 1024 / 1024 / 1024)) + "G");
        double[] doubles1 = new double[2];
        doubles1[0] = list.get(1)
                .getInfo();
        doubles1[1] = list.get(1)
                .getTotal();
        customPercentView2.setPercent(doubles1);
        room_reduce.setText("已使用" + decimalFormat.format(avail) + "G/" + decimalFormat.format(total) + "G");
    }

    @Override
    public void onFailed() {
    }
}
