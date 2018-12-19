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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.activity.facility.MobileActivity;
import com.languang.bluebox.activity.initialize.SettingPwdActivity;
import com.languang.bluebox.activity.initialize.SettingWanActivity;
import com.languang.bluebox.activity.login.LoginActivity;
import com.languang.bluebox.basework.costom.DefaultCode;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.constant.Constant;
import com.languang.bluebox.entity.NetPort;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.entity.SpeRes;
import com.languang.bluebox.entity.login.LoginRes;
import com.languang.bluebox.presenter.IFacilityInfo;
import com.languang.bluebox.utils.MyUtil;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ScreenUtilBase;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动页
 *
 * @author 49829
 * @date 2018/4/16
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            loginOrMain();
        }
        checkInfo();
    }

    private void checkInfo() {
        String url = ApiConstant.WLAN_INFO;
        OkHttpUtils.getInstance()
                .okPost(this, url, null, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        ResponseMessage<NetPort> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<NetPort>>() {
                        }.getType());
                        NetPort netPort = responseMessage.getData();
//                        responseMessage.setRet();
                        if (responseMessage.getRet() == 404) {
                            OkHttpUtils.getInstance()
                                    .okPost(SplashActivity.this, ApiConstant.CLOUD_WLAN_INFO, null, new OkHttpCallBack() {
                                        @Override
                                        public void onSucceed(String requestUrl, String response) {
                                            Log.d("ccnb", response);
                                            SpeRes speRes = new Gson().fromJson(response, SpeRes.class);
                                            if (speRes.getRet() == 200) {
                                                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                            }
                                        }

                                        @Override
                                        public void onFailed() {
                                        }
                                    });
                        } else if (responseMessage.getRet() == 200) {
                            MMKV.defaultMMKV()
                                    .encode("user", new Gson().toJson(netPort));
                        }
                        if (netPort.isActivate()) {
                            manLogin(true);
                        } else {
                            manLogin(false);
                        }
                    }

                    @Override
                    public void onFailed() {
                        Log.d("ccnb", "fail");
                    }
                });
    }

    private void manLogin(boolean isActive) {
        Map<String, Object> params = new HashMap<>(1);
        if (isActive) {
            params.put("type", "pwd");
            params.put("pwd", "111");
            params.put("imei", MyUtil.getImei(this));
        } else {
            params.put("type", "new");
        }
        OkHttpUtils.getInstance()
                .okPost(this, ApiConstant.WLAN_SYS_LOGIN, params, new OkHttpCallBack() {
                    @Override
                    public void onSucceed(String requestUrl, String response) {
                        ResponseMessage<LoginRes> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<LoginRes>>() {
                        }.getType());
                        LoginRes loginRes = responseMessage.getData();
                        if (loginRes.getToken() == null) {
                            manLogin(true);
                        } else {
                            MMKV.defaultMMKV()
                                    .encode("token", loginRes.getToken());
                            Intent intent = new Intent(SplashActivity.this, SettingPwdActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailed() {
                        Log.d("ccnb", "fail");
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
            if (isAllGranted) {
            }
            loginOrMain();
        }
    }
}
