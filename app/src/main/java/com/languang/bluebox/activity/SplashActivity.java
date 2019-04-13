package com.languang.bluebox.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.ErrorActivity;
import com.languang.bluebox.LogFiles;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.initialize.SettingPwdActivity;
import com.languang.bluebox.activity.login.BoxLoginActivity;
import com.languang.bluebox.activity.login.LoginActivity;
import com.languang.bluebox.basework.costom.DefaultCode;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.SpeRes;
import com.languang.bluebox.entity.login.LoginRes;
import com.languang.bluebox.utils.MyUtil;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ScreenUtilBase;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 启动页
 *
 * @author 49829
 * @date 2018/4/16
 */
public class SplashActivity extends AppCompatActivity {
    public static String wlanIp;
    com.afollestad.materialdialogs.MaterialDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> strings = new ArrayList<>();
        strings.add("线上模拟");
        strings.add("真实盒子");
         new com.afollestad.materialdialogs.MaterialDialog.Builder(this).items(strings)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (position == 0) {
                            TimeUtils.setGateway(SplashActivity.this, false);
                            TimeUtils.setWlanIp(false, null);
                        } else {
                            TimeUtils.setGateway(SplashActivity.this, true);
                        }
                        checkInfo();
                    }
                })
                .show();
        ScreenUtilBase.setStatusBarColor(this, DefaultCode.G_STATUS_BAR_COLOR);
        if (!checkPermissionAllGranted(
                new String[]{
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }
        )) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 10001);
        } else {
        }
    }

    private void checkInfo() {
        OkHttpUtils.getInstance()
                .okPost(this, TimeUtils.getGateway() + "/info", null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        Log.d("ccnb1111", response);
                        try {
                            ResponseMessage<NetPort> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<NetPort>>() {
                            }.getType());
                            int a;
                            NetPort netPort = responseMessage.getData();
                            if (responseMessage.getData()
                                    .getStatus()
                                    .equals("9999")) {
                                Toast.makeText(SplashActivity.this, "访问网关成功" + response, Toast.LENGTH_SHORT)
                                        .show();
                                MMKV.defaultMMKV()
                                        .encode("user", new Gson().toJson(netPort));
                                if (netPort.isActivate()) {
                                    Intent intent = new Intent(SplashActivity.this, BoxLoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    manLogin();
                                }
                            } else {
                                OkHttpUtils.getInstance()
                                        .okPost(SplashActivity.this, ApiConstant.CLOUD_WLAN_INFO, null, new OkHttpCallBack() {
                                            @Override
                                            public void onSucceed(String requestUrl, String response) {
                                                LogFiles.d("cctag", requestUrl + ":" + response);
                                                SpeRes<SpeRes.DataBean> speRes = new Gson().fromJson(response, new TypeToken<SpeRes<SpeRes.DataBean>>() {
                                                }.getType());
                                                if (speRes.getData()
                                                        .getStatus()
                                                        .equals("9999")) {
                                                    Toast.makeText(SplashActivity.this, "访问云端成功", Toast.LENGTH_SHORT)
                                                            .show();
                                                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }

                                            @Override
                                            public void onFailed() {
                                            }
                                        });
                            }
                        } catch (Exception e) {
                            OkHttpUtils.getInstance()
                                    .okPost(SplashActivity.this, ApiConstant.CLOUD_WLAN_INFO, null, new OkHttpCallBack() {
                                        @Override
                                        public void onSucceed(String requestUrl, String response) {
                                            LogFiles.d("cctag", requestUrl + ":" + response);
                                            SpeRes<SpeRes.DataBean> speRes = new Gson().fromJson(response, new TypeToken<SpeRes<SpeRes.DataBean>>() {
                                            }.getType());
                                            if (speRes.getData()
                                                    .getStatus()
                                                    .equals("9999")) {
                                                Toast.makeText(SplashActivity.this, "访问外网成功" + response, Toast.LENGTH_SHORT)
                                                        .show();
                                                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(SplashActivity.this, "访问网关失败" + response, Toast.LENGTH_SHORT)
                                                        .show();
                                            }
                                        }

                                        @Override
                                        public void onFailed() {
                                            Toast.makeText(SplashActivity.this, "访问网关未响应", Toast.LENGTH_SHORT)
                                                    .show();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onFailed() {
                        OkHttpUtils.getInstance()
                                .okPost(SplashActivity.this, ApiConstant.CLOUD_WLAN_INFO, null, new OkHttpCallBack() {
                                    @Override
                                    public void onSucceed(String requestUrl, String response) {
                                        LogFiles.d("cctag", requestUrl + ":" + response);
                                        SpeRes<SpeRes.DataBean> speRes = new Gson().fromJson(response, new TypeToken<SpeRes<SpeRes.DataBean>>() {
                                        }.getType());
                                        if (speRes.getData()
                                                .getStatus()
                                                .equals("9999")) {
                                            Toast.makeText(SplashActivity.this, "访问外网成功" + response, Toast.LENGTH_SHORT)
                                                    .show();
                                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(SplashActivity.this, "访问网关失败" + response, Toast.LENGTH_SHORT)
                                                    .show();
                                        }
                                    }

                                    @Override
                                    public void onFailed() {
                                        Toast.makeText(SplashActivity.this, "访问网关未响应", Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                });
                    }
                });
    }

    private void manLogin() {
        Map<String, Object> params = new HashMap<>(1);
//        if (isActive) {
//            params.put("type", "pwd");
//            params.put("pwd", "111");
        params.put("imei", MyUtil.getImei(this));
        params.put("type", "new");
        OkHttpUtils.getInstance()
                .okPost(this, TimeUtils.getGateway() + "/syslogin", params, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        LogFiles.d("cctag", requestUrl + ":" + response);
                        try {
                            ResponseMessage<LoginRes> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<LoginRes>>() {
                            }.getType());
                            LoginRes loginRes = responseMessage.getData();
                            //如果为9999
                            if (loginRes.getStatus()
                                    .equals("9999")) {
                                Toast.makeText(SplashActivity.this, "登录盒子成功" + response, Toast.LENGTH_SHORT)
                                        .show();
                                if (loginRes.getToken() == null) {
                                    Intent intent = new Intent(SplashActivity.this, BoxLoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SplashActivity.this, "登录盒子失败" + response, Toast.LENGTH_SHORT)
                                            .show();
                                    MMKV.defaultMMKV()
                                            .encode("token", loginRes.getToken());
                                    Intent intent = new Intent(SplashActivity.this, SettingPwdActivity.class);
                                    startActivity(intent);
                                }
                                finish();
                            }
                        } catch (Exception e) {
                            Intent intent = new Intent(SplashActivity.this, ErrorActivity.class);
                            intent.putExtra("req", requestUrl);
                            intent.putExtra("res", response);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(SplashActivity.this, "登录失败", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    //    private void checkCloudInfo() {
//        String url = ApiConstant.CLOUD_WLAN_INFO;
//        OkHttpUtils.getInstance()
//                .okPost(this, url, null, this);
//    }
    private void loginOrMain() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 第 3 步: 申请权限结果返回处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10001) {
            boolean isAllGranted = true;
            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
//            OkHttpUtils
            if (isAllGranted) {
            }
            loginOrMain();
        }
    }
}
