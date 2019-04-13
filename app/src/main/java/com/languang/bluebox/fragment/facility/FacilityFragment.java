package com.languang.bluebox.fragment.facility;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.InfoActivity;
import com.languang.bluebox.LoadingPopView;
import com.languang.bluebox.R;
import com.languang.bluebox.SetNameInterface;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.login.MesBean;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.SpeRes;
import com.languang.bluebox.entity.facility.FacilityListInfo;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.xuexiang.xui.widget.popupwindow.easypopup.EasyPopup;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 设备页面
 *
 * @author 49829
 * @date 2018/3/26
 */
public class FacilityFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    MyInfoDataAdapter adapter;
    WindowManager.LayoutParams ll;
    List<FacilityListInfo> listInfos = new ArrayList<>();
    EditText editText;
    LoadingPopView loadingPopView;
    private EasyPopup mCirclePop;
    int a;
    ImageView imageView1;
    String mobile;
    String uuid;
    FacilityListInfo info;
    Bitmap bitmap;
    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
            bitmap = resource;
            imageView1.setImageBitmap(bitmap);
        }
    };

    @SuppressLint("ValidFragment")
    public FacilityFragment(int a) {
        this.a = a;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_facility;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dddd", a + "");
        loadingPopView = new LoadingPopView(getActivity());
        ll = getActivity().getWindow()
                .getAttributes();
        OkHttpUtils.getInstance()
                .okPost(getActivity(), ApiConstant.BLUES_LIST, null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        Log.d("ccnb1111", response);
                        ResponseMessage1<List<FacilityListInfo>> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage1<List<FacilityListInfo>>>() {
                        }.getType());
                        if (responseMessage.getRet() == 200) {
//                            for (FacilityListInfo facilityListInfo : responseMessage.getData()) {
////                                facilityListInfo.setOnline(TimeUtils.isOnline(FacilityFragment.this.getActivity(), "15.16.22.24"));
//                            }
                            for (int i = 0; i < responseMessage.getData()
                                    .size(); i++) {
                                if (i == a) {
                                    responseMessage.getData()
                                            .get(i)
                                            .setOnline(true);
                                    info = responseMessage.getData()
                                            .get(i);
                                    mobile = responseMessage.getData()
                                            .get(i)
                                            .getMobile();
                                    uuid = responseMessage.getData()
                                            .get(i)
                                            .getBlueuuid();
                                }
                            }
                            listInfos.addAll(responseMessage.getData());
                            adapter.notifyDataSetChanged();
                            OkHttpUtils.getInstance()
                                    .okPost(getActivity(), TimeUtils.getGateway() + "/info", null, new OkHttpCallBack() {
                                        @Override
                                        public void onSucceed(String requestUrl, String response) {
                                            ResponseMessage<NetPort> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<NetPort>>() {
                                            }.getType());
//                                            mCirclePop = new EasyPopup(getContext())
//                                                    .setContentView(R.layout.ss)
//                                                    .setFocusAndOutsideEnable(true)
//                                                    .createPopup();
//                                            ImageView imageView = mCirclePop.getView(R.id.image);
                                        }

                                        @Override
                                        public void onFailed() {
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onFailed() {
                    }
                });
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        final PopupWindow popupWindow = new PopupWindow();
//        popupWindow.setWidth(800);
//        popupWindow.setHeight(400);
//        popupWindow.setFocusable(true);
//        View view1 = getLayoutInflater().inflate(R.layout.rename, null);
//        Button cancel = view1.findViewById(R.id.cancel);
//        Button yes = view1.findViewById(R.id.yes);
//        editText = view1.findViewById(R.id.edit);
//        yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadingPopView.show();
//                Map<String, Object> map = new HashMap<>();
//                map.put("name", editText.getText()
//                        .toString());
//                OkHttpUtils.getInstance()
//                        .okPost(getActivity(), TimeUtils.getWlanIp() + "/setbox", null, new OkHttpCallBack() {
//                            @Override
//                            public void onSucceed(String requestUrl, String response) {
//                                ResponseMessage<SetBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<SetBean>>() {
//                                }.getType());
//                                if (responseMessage.getData()
//                                        .getStatus()
//                                        .equals("9999")) {
//                                    loadingPopView.dissmiss();
//                                    ll.alpha = 1f;
//                                    getActivity().getWindow()
//                                            .setAttributes(ll);
//                                    popupWindow.dismiss();
//                                }
//                            }
//
//                            @Override
//                            public void onFailed() {
//                                loadingPopView.dissmiss();
//                            }
//                        });
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ll.alpha = 1f;
//                getActivity().getWindow()
//                        .setAttributes(ll);
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow.setContentView(view1);
        adapter = new MyInfoDataAdapter(getActivity(), listInfos, new SetNameInterface() {
            @Override
            public void click() {
//                ll.alpha = 0.5f;
//                getActivity().getWindow()
//                        .setAttributes(ll);
//                popupWindow.showAtLocation(getActivity().getWindow()
//                        .getDecorView(), Gravity.CENTER, 0, 0);
                new MaterialDialog.Builder(getActivity())
                        .title("重命名")
                        .inputType(
                                InputType.TYPE_CLASS_TEXT)
                        .input(
                                "",
                                "",
                                false, new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(@NonNull final MaterialDialog dialog, CharSequence input) {
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("name", input);
                                        OkHttpUtils.getInstance()
                                                .okPost(getActivity(), TimeUtils.getWlanIp() + "/setbox", map, new OkHttpCallBack() {
                                                    @Override
                                                    public void onSucceed(String requestUrl, String response) {
                                                        ResponseMessage<SetBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<SetBean>>() {
                                                        }.getType());
                                                        if (responseMessage.getData()
                                                                .getStatus()
                                                                .equals("9999")) {
                                                            Log.d("cccc", "in");
                                                            dialog.dismiss();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailed() {
                                                        Log.d("cccc", "in1");
                                                        dialog.dismiss();
                                                    }
                                                });
                                    }
                                }
                        )
                        .positiveText("完成")
                        .negativeText("我不输了")
                        .cancelable(false)
                        .show();
            }
        }, new MyInfoDataAdapter.click() {
            @Override
            public void click(int p) {
                ll.alpha = 0.5f;
                getActivity().getWindow()
                        .setAttributes(ll);
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setWidth(700);
                popupWindow.setHeight(700);
                popupWindow.setOutsideTouchable(true);
                View view = (getActivity()).getLayoutInflater()
                        .inflate(R.layout.loading1, null);
                imageView1 = view.findViewById(R.id.iamge);
                TextView share = view.findViewById(R.id.share);
                TextView save = view.findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//如果是4.4及以上版本
                            Intent mediaScanIntent = new Intent(
                                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            Uri contentUri = Uri.fromFile(new File(TimeUtils.saveBitmap(getActivity(), bitmap))); //out is your output file
                            mediaScanIntent.setData(contentUri);
                            getActivity().sendBroadcast(mediaScanIntent);
                        } else {
                            getActivity().sendBroadcast(new Intent(
                                    Intent.ACTION_MEDIA_MOUNTED,
                                    Uri.parse("file://"
                                            + Environment.getExternalStorageDirectory())));
                        }
                        Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, TimeUtils.saveBitmap(bitmap, TimeUtils.getCurrentTime()));
                        shareIntent.setType("image/*");
                        startActivity(Intent.createChooser(shareIntent, "分享图片"));
                    }
                });
                popupWindow.setContentView(view);
                popupWindow.showAtLocation(
                        getActivity()
                                .
                                        getWindow()
                                .
                                        getDecorView(), Gravity.CENTER, 0, 0);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ll.alpha = 1f;
                        getActivity().getWindow()
                                .setAttributes(ll);
                    }
                });
                Glide.with(
                        getActivity())
                        .
                                asBitmap()
                        .
                                load(TimeUtils.getWlanIp() + "/public/" + "default/" + info.getBlueuuid() + ".png")
                        .
                                into(target);
            }
        }, new MyInfoDataAdapter.click1() {
            @Override
            public void click(int p) {
                OkHttpUtils.getInstance()
                        .okPost(getActivity(), ApiConstant.SEND_SMS_URL, new HashMap<String, Object>() {{
                                    put("mobile", mobile);
                                    put("type", "pullscode");
                                    put("blueuuid", uuid);
                                }}
                                ,
                                new OkHttpCallBack() {
                                    @Override
                                    public void onSucceed(String requestUrl, String response) {
                                        SpeRes<MesBean> m = new Gson().fromJson(response, new TypeToken<SpeRes<MesBean>>() {
                                        }.getType());
                                        if (m.getData()
                                                .getStatus()
                                                .equals("9999")) {
                                            Toast.makeText(getActivity(), m.getData()
                                                    .getMsg(), Toast.LENGTH_SHORT)
                                                    .show();
                                        }
                                    }

                                    @Override
                                    public void onFailed() {
                                    }
                                });
            }
        }, new MyInfoDataAdapter.infoClick() {
            @Override
            public void click(int p) {
                OkHttpUtils.getInstance()
                        .okPost(getActivity(), ApiConstant.BOX_WAISHE_INFO, new HashMap<String, Object>() {{
                            put("type", "dev");
                        }}, new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                ResponseMessage<HeziBean> heziBean = new Gson().fromJson(response, new TypeToken<ResponseMessage<HeziBean>>() {
                                }.getType());
                                Intent intent = new Intent(getActivity(), InfoActivity.class);
                                intent.putExtra("a", new Gson().toJson(info));
                                intent.putExtra("b", new Gson().toJson(heziBean.getData()));
                                startActivity(intent);
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
    }
//    @OnClick({R.id.my_wifi_rl
//            , R.id.facility_info_ll
//            , R.id.disk_manage_ll
//            , R.id.pwd_manage_ll
//            , R.id.visitor_pwd_ll
//            , R.id.restart})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.my_wifi_rl:
//                startActivity(new Intent(getActivity(), MyWifiActivity.class));
//                break;
//            case R.id.facility_info_ll:
//                startActivity(new Intent(getActivity(), FacilityInfoActivity.class));
//                break;
//            case R.id.disk_manage_ll:
//                startActivity(new Intent(getActivity(), DiskManageActivity.class));
//                break;
//            case R.id.pwd_manage_ll:
//                startActivity(new Intent(getActivity(), AdminPwdActivity.class));
//                break;
//            case R.id.visitor_pwd_ll:
//                startActivity(new Intent(getActivity(), VisitorPwdActivity.class));
//                break;
//            case R.id.restart:
//                OkHttpUtils.getInstance().okGet(getActivity(), "http://result.eolinker.com/vaYZizz7dd1c49fc140f8522e29f5418e4293e5ff137f38?uri=/CDMeta", new OkHttpCallBack() {
//                    @Override
//                    public void onSucceed(String requestUrl, String response) {
//                        Log.d(TAG, "onSucceed: " + response);
//                    }
//
//                    @Override
//                    public void onFailed() {
//
//                    }
//                });
//                break;
//            default:
//                break;
//        }
//    }
}
