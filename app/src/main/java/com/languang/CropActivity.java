package com.languang;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.edmodo.cropper.CropImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.Onclick;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.ImgEntity;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CropActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private static final String SD_PATH = "/sdcard/DCIM/Camera/";
    private static final String IN_PATH = "/dskqxt/pic/";
    SuoLueAdapter3 adapter;
    LvJingAdapter adapter1;
    float one = 1, two = 1, three = 1;
    RecyclerView recyclerView;
    CropImageView cropImageView;
    List<ImgEntity> choose;
    List<ImgEntity> choosed = new ArrayList<>();
    ImageView caijian;
    ImageView yes;
    ImageView lvjing;
    ImgEntity entity;
    ImageView duibidu;
    RecyclerView recyclerView1;
    LinearLayout seek;
    SeekBar ContrastseekBar;
    SeekBar BrightnessseekBar;
    SeekBar SaturationseekBar;
    ImageView ima;
    Bitmap bitmap;
    Bitmap result;
    List<String> strings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop);
        recyclerView1 = findViewById(R.id.recycler_view1);
        seek = findViewById(R.id.seek);
        caijian = findViewById(R.id.caijian);
        lvjing = findViewById(R.id.lvjing);
        duibidu = findViewById(R.id.duibi);
        ima = findViewById(R.id.ima);
        yes = findViewById(R.id.yes);
        ContrastseekBar = findViewById(R.id.ContrastseekBar);
        BrightnessseekBar = findViewById(R.id.BrightnessseekBar);
        SaturationseekBar = findViewById(R.id.SaturationseekBar);
        BrightnessseekBar.setOnSeekBarChangeListener(this);
        SaturationseekBar.setOnSeekBarChangeListener(this);
        ContrastseekBar.setOnSeekBarChangeListener(this);
        caijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ima.setVisibility(View.GONE);
                seek.setVisibility(View.GONE);
                cropImageView.setVisibility(View.VISIBLE);
                recyclerView1.setVisibility(View.GONE);
                caijian.setImageDrawable(getResources().getDrawable(R.drawable.ic_jianqie_3));
                lvjing.setImageDrawable(getResources().getDrawable(R.drawable.ic_lvjing));
                duibidu.setImageDrawable(getResources().getDrawable(R.drawable.ic_14));
                yes.setImageDrawable(getResources().getDrawable(R.drawable.ic_tubiaolunkuo__2));
            }
        });
        lvjing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ima.setVisibility(View.VISIBLE);
                cropImageView.setVisibility(View.INVISIBLE);
                recyclerView1.setVisibility(View.VISIBLE);
                seek.setVisibility(View.GONE);
                caijian.setImageDrawable(getResources().getDrawable(R.drawable.ic_jianqie_2));
                lvjing.setImageDrawable(getResources().getDrawable(R.drawable.ic_lvjing_2));
                duibidu.setImageDrawable(getResources().getDrawable(R.drawable.ic_14));
                yes.setImageDrawable(getResources().getDrawable(R.drawable.ic_tubiaolunkuo__2));
            }
        });
        duibidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ima.setVisibility(View.VISIBLE);
                cropImageView.setVisibility(View.GONE);
                recyclerView1.setVisibility(View.GONE);
                seek.setVisibility(View.VISIBLE);
                caijian.setImageDrawable(getResources().getDrawable(R.drawable.ic_jianqie_2));
                lvjing.setImageDrawable(getResources().getDrawable(R.drawable.ic_lvjing));
                duibidu.setImageDrawable(getResources().getDrawable(R.drawable.ic_14_2));
                yes.setImageDrawable(getResources().getDrawable(R.drawable.ic_tubiaolunkuo__2));
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strings = new ArrayList<>();
                strings.add("本地保存");
                strings.add("上传盒子");
                MaterialDialog.Builder dialog = new MaterialDialog.Builder(CropActivity.this).items(strings)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                if (cropImageView.getVisibility() == View.VISIBLE) {
                                    if (position == 0) {
                                        saveBitmap(CropActivity.this, cropImageView.getCroppedImage());
                                        finish();
                                    } else {
                                        OkHttpUtils.getInstance()
                                                .uploadFile(CropActivity.this, TimeUtils.getWlanIp() + "/upload", saveBitmap(CropActivity.this, cropImageView.getCroppedImage()), new OkHttpCallBack() {
                                                    @Override
                                                    public void onSucceed(String requestUrl, String response) {
                                                        Log.d("cctag", response);
                                                        finish();
                                                    }

                                                    @Override
                                                    public void onFailed() {
                                                    }
                                                });
                                    }
                                } else if (seek.getVisibility() == View.VISIBLE) {
                                    if (position == 0) {
                                        saveBitmap(CropActivity.this, bitmap);
                                        finish();
                                    } else {
                                        OkHttpUtils.getInstance()
                                                .uploadFile(CropActivity.this, TimeUtils.getWlanIp() + "/upload", saveBitmap(CropActivity.this, result), new OkHttpCallBack() {
                                                    @Override
                                                    public void onSucceed(String requestUrl, String response) {
                                                        Log.d("cctag", response);
                                                        finish();
                                                    }

                                                    @Override
                                                    public void onFailed() {
                                                    }
                                                });
                                    }
                                } else {
                                    if (position == 0) {
                                        saveBitmap(CropActivity.this, bitmap);
                                        finish();
                                    } else {
                                        OkHttpUtils.getInstance()
                                                .uploadFile(CropActivity.this, TimeUtils.getWlanIp() + "/upload", saveBitmap(CropActivity.this, result), new OkHttpCallBack() {
                                                    @Override
                                                    public void onSucceed(String requestUrl, String response) {
                                                        Log.d("cctag", response);
                                                        finish();
                                                    }

                                                    @Override
                                                    public void onFailed() {
                                                    }
                                                });
                                    }
                                }
                            }
                        });
                dialog.show();
                caijian.setImageDrawable(getResources().getDrawable(R.drawable.ic_jianqie_2));
                lvjing.setImageDrawable(getResources().getDrawable(R.drawable.ic_lvjing));
                duibidu.setImageDrawable(getResources().getDrawable(R.drawable.ic_14));
                yes.setImageDrawable(getResources().getDrawable(R.drawable.ic_tubiaolunkuo_));
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        cropImageView = findViewById(R.id.imageview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView1.setLayoutManager(layoutManager1);
        choose = new Gson().fromJson(getIntent().getStringExtra("img"), new TypeToken<List<ImgEntity>>() {
        }.getType());
        Glide.with(CropActivity.this)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + choose.get(0)
                        .getSrcpath() + choose.get(0)
                        .getSrcname())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        cropImageView.setImageBitmap(resource);
                    }
                });
        Glide.with(CropActivity.this)
                .asBitmap()
                .load(TimeUtils.getWlanIp() + "/public/" + choose.get(0)
                        .getSrcpath() + choose.get(0)
                        .getSrcname())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        bitmap = resource;
                        ima.setImageBitmap(resource);
                    }
                });
        adapter1 = new LvJingAdapter(this, choose.get(0), new LvjingInterface() {
            @Override
            public void click(final Bitmap bitmap) {
                ima.setImageBitmap(bitmap);
                result = bitmap;
//                strings = new ArrayList<>();
//                strings.add("本地保存");
//                strings.add("上传盒子");
//                MaterialDialog.Builder dialog = new MaterialDialog.Builder(CropActivity.this).items(strings)
//                        .itemsCallback(new MaterialDialog.ListCallback() {
//                            @Override
//                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
//                                if (position == 0) {
//                                    saveBitmap(CropActivity.this, bitmap);
//                                    finish();
//                                } else {
//                                    OkHttpUtils.getInstance()
//                                            .uploadFile(CropActivity.this, TimeUtils.getWlanIp() + "/upload", saveBitmap(CropActivity.this, bitmap), new OkHttpCallBack() {
//                                                @Override
//                                                public void onSucceed(String requestUrl, String response) {
//                                                    Log.d("cctag", response);
//                                                    finish();
//                                                }
//
//                                                @Override
//                                                public void onFailed() {
//                                                }
//                                            });
//                                }
//                            }
//                        });
//                dialog.show();
            }
        });
        choose.get(0)
                .setChecked(true);
        recyclerView1.setAdapter(adapter1);
        cropImageView.setGuidelines(1);
        adapter = new
                SuoLueAdapter3(this, choose, new Onclick() {
            @Override
            public void click(int p, int count, boolean isadd, ImgEntity imgEntity) {
                entity = imgEntity;
                Glide.with(CropActivity.this)
                        .asBitmap()
                        .load(TimeUtils.getWlanIp() + "/public/" + imgEntity
                                .getSrcpath() + imgEntity
                                .getSrcname())
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                cropImageView.setImageBitmap(resource);
                                ima.setImageBitmap(resource);
                                bitmap = resource;
                                SaturationseekBar.setProgress(100);
                                BrightnessseekBar.setProgress(127);
                                ContrastseekBar.setProgress(63);
                            }
                        });
                adapter1.set1(imgEntity);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState()
                .equals(
                        Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
            Log.d("cctag", "1");
        } else {
            savePath = context.getApplicationContext()
                    .getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
            Log.d("cctag", "2");
        }
        Log.d("cctag", savePath);
        try {
            filePic = new File(savePath + System.currentTimeMillis() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile()
                        .mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(filePic);
        intent.setData(uri);
        context.sendBroadcast(intent);
        return filePic.getAbsolutePath();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.ContrastseekBar:
                one = (float) ((seekBar.getProgress() + 64) / 128.0);
                break;
            case R.id.BrightnessseekBar:
                two = seekBar.getProgress() - 127;
                break;
            case R.id.SaturationseekBar:
                three = seekBar.getProgress() / 100;
                break;
        }
        Bitmap copyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(copyBitmap);
//        ColorMatrix colorMatrixS = new ColorMatrix();
//        colorMatrixS.setRotate(0, one);
//        colorMatrixS.setRotate(1, one);
//        colorMatrixS.setRotate(2, one);
        ColorMatrix colorMatrixS = new ColorMatrix();
        colorMatrixS.set(new float[]{
                one, 0, 0, 0, 0, 0,
                one, 0, 0, 0,// 改变对比度
                0, 0, one, 0, 0, 0, 0, 0, 1, 0
        });
//        ColorMatrix colorMatrixL = new ColorMatrix();
//        colorMatrixL.setScale(two, two, two, 1);
        ColorMatrix colorMatrixL = new ColorMatrix();
        colorMatrixL.set(new float[]{
                1, 0, 0, 0, two, 0, 1,
                0, 0, two,// 改变亮度
                0, 0, 1, 0, two, 0, 0, 0, 1, 0
        });
        ColorMatrix colorMatrixB = new ColorMatrix();
        colorMatrixB.setSaturation(three);
        ColorMatrix colorMatriximg = new ColorMatrix();
        //        通过postConcat()方法可以将以上效果叠加到一起
        colorMatriximg.postConcat(colorMatrixB);
        colorMatriximg.postConcat(colorMatrixL);
        colorMatriximg.postConcat(colorMatrixS);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatriximg);
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(bitmap, new Matrix(), paint);
        ima.setImageBitmap(copyBitmap);
        result = copyBitmap;
    }
}
