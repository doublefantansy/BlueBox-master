package com.languang.bluebox;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.languang.CropActivity;
import com.languang.bluebox.activity.VoiceBean;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoChuActivity extends AppCompatActivity {
    SuoLueAdapter1 adapter;
    RecyclerView recyclerView;
    ImageView imageView;
    TextView title;
    LinearLayout daochu;
    LinearLayout meihua;
    LinearLayout yuyin;
    LinearLayout share;
    List<ImgEntity> imgEntities = new ArrayList<>();
    List<ImgEntity> choose = new ArrayList<>();
    List<File> files;
    ImageView anim;
    ImageView anim1;
    ImgListEntity imgListEntity;
    int count = 0;
    LoadingPopView popView;
    private AnimationDrawable drawable;
    private ArrayList<Uri> imageUris = new ArrayList<>();
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_chu);
        popView = new LoadingPopView(this);
        title = findViewById(R.id.title);
        share = findViewById(R.id.share);
        yuyin = findViewById(R.id.yuyin);
        anim = findViewById(R.id.ani);
        anim1 = findViewById(R.id.ani1);
        imageView = findViewById(R.id.image);
        recyclerView = findViewById(R.id.recycler_view);
        daochu = findViewById(R.id.daochu);
        meihua = findViewById(R.id.meihua);
        drawable = (AnimationDrawable) anim.getBackground();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                imageUris.clear();
                for (ImgEntity imgEntity : choose) {
                    Glide.with(DaoChuActivity.this)
                            .asBitmap()
                            .load(TimeUtils.getWlanIp() + "/public/" + imgEntity
                                    .getSrcpath() + imgEntity
                                    .getSrcname())
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                    bitmap = resource;
                                    count++;
                                    File file = new File(CropActivity.saveBitmap(DaoChuActivity.this, bitmap));
                                    imageUris.add(Uri.fromFile(file));
                                    if (count == choose.size()) {
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
        yuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (choose.size() == 1) {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    VoiceBean path = choose.get(0)
                            .getVoice();
                    if (path != null) {
                        try {
                            anim1.setVisibility(View.GONE);
                            anim.setVisibility(View.VISIBLE);
                            Toast.makeText(DaoChuActivity.this, "开始播放", Toast.LENGTH_SHORT)
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
                                Toast.makeText(DaoChuActivity.this, "播放完毕", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                    } else {
                        Toast.makeText(DaoChuActivity.this, "该图片没有相应的音频", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    Toast.makeText(DaoChuActivity.this, "只能播放一个音频", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        daochu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popView.show();
                if (choose.size() != 0) {
                    final Map<String, Object> map = new HashMap<>();
                    for (ImgEntity imgEntity : choose) {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("files", imgEntity.getUuid());
                        OkHttpUtils.getInstance()
                                .okPost(DaoChuActivity.this, TimeUtils.getWlanIp() + "/fileout", map1, new OkHttpCallBack() {
                                    @Override
                                    public void onSucceed(String requestUrl, String response) {
                                        Log.d("ccnb", response);
                                        count++;
                                        if (count == choose.size()) {
                                            finish();
                                            popView.dissmiss();
                                        }
                                    }

                                    @Override
                                    public void onFailed() {
                                        Log.d("ccnb", "fail");
                                    }
                                });
                    }
//                }
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new
                SuoLueAdapter1(this, imgEntities, new Onclick() {
            @Override
            public void click(int p, int count, boolean isadd, ImgEntity imgEntity) {
                if (isadd) {
//                    BiaoZhuActivity.this.imgEntity = imgEntity;
                    Glide.with(DaoChuActivity.this)
                            .asBitmap()
                            .load(TimeUtils.getWlanIp() + "/public/" + imgEntity
                                    .getSrcpath() + imgEntity
                                    .getSrcname())
                            .into(imageView);
//                    title.setText(imgEntity
//                            .getCdate());
                    choose.add(imgEntity);
                } else {
                    choose.remove(imgEntity);
                }
                Log.d("ccnb", choose.size() + "");
            }
        });
        recyclerView.setAdapter(adapter);
        imgListEntity = new
                Gson().fromJson(getIntent().
                getStringExtra("spe"), ImgListEntity.class);
        imgEntities.addAll((new
                Gson().fromJson(getIntent().
                getStringExtra("spe"), ImgListEntity.class)).getImgEntityList());
        title.setText(imgListEntity.getTime());
        adapter.notifyDataSetChanged();
        meihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (choose.size() == 1) {
                    Intent intent = new Intent(DaoChuActivity.this, CropActivity.class);
                    intent.putExtra("img", new Gson().toJson(choose));
                    startActivityForResult(intent, 1);
                } else if (choose.size() == 0) {
                    Toast.makeText(DaoChuActivity.this, "请选择图片", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(DaoChuActivity.this, "只能选择1张图片", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
