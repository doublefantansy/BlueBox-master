package com.languang.bluebox;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.google.gson.reflect.TypeToken;
import com.languang.CropActivity;
import com.languang.bluebox.entity.ImgEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BiaoZhuActivity extends AppCompatActivity {
    SuoLueAdapter1 adapter;
    RecyclerView recyclerView;
    ImageView imageView;
    TextView title;
    LinearLayout ss;
    LinearLayout ss1;
    LinearLayout meihua;
    LinearLayout biaozhu;
    LinearLayout piliang;
    LinearLayout share;
    List<ImgEntity> imgEntities;
    List<ImgEntity> imgEntityArrayList = new ArrayList<>();
    ImgEntity imgEntity;
    private int count = 0;
    private ArrayList<Uri> imageUris = new ArrayList<Uri>();
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biao_zhu);
        recyclerView = findViewById(R.id.recycler_view);
        share = findViewById(R.id.share);
        piliang = findViewById(R.id.piliang);
        biaozhu = findViewById(R.id.biaozhu);
        imageView = findViewById(R.id.image);
        title = findViewById(R.id.title);
        ss = findViewById(R.id.ss);
        ss1 = findViewById(R.id.ss1);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                imageUris.clear();
                for (ImgEntity imgEntity : imgEntityArrayList) {
                    Glide.with(BiaoZhuActivity.this)
                            .asBitmap()
                            .load(TimeUtils.getWlanIp() + "/public/" + imgEntity
                                    .getSrcpath() + imgEntity
                                    .getSrcname())
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                    bitmap = resource;
                                    count++;
                                    File file = new File(CropActivity.saveBitmap(BiaoZhuActivity.this, bitmap));
                                    imageUris.add(Uri.fromFile(file));
                                    if (count == imgEntityArrayList.size()) {
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
        biaozhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BiaoZhuActivity.this, AddPic1Activity.class);
                intent.putExtra("spe", new Gson().toJson(imgEntityArrayList));
                startActivity(intent);
                finish();
            }
        });
//        biaozhu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BiaoZhuActivity.this, AddPic1Activity.class);
//                intent.putExtra("spe", new Gson().toJson(imgEntityArrayList));
//                startActivity(intent);
//                finish();
//            }
//        });
        meihua = findViewById(R.id.meihua11);
        meihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgEntityArrayList.size() == 1) {
                    Intent intent = new Intent(BiaoZhuActivity.this, CropActivity.class);
                    intent.putExtra("img", new Gson().toJson(imgEntityArrayList));
                    startActivityForResult(intent, 1);
                } else if (imgEntityArrayList.size() == 0) {
                    Toast.makeText(BiaoZhuActivity.this, "请选择图片", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(BiaoZhuActivity.this, "只能选择1张图片", Toast.LENGTH_SHORT)
                            .show();
                }
//                Log.d("cctagcctag", "in");
//                Intent intent = new Intent(BiaoZhuActivity.this, CropActivity.class);
//                intent.putExtra("img", new Gson().toJson(imgEntityArrayList));
//                startActivityForResult(intent, 1);
            }
        });
        imgEntities = new Gson().fromJson(getIntent().getStringExtra("json"), new TypeToken<List<ImgEntity>>() {
        }.getType());
        adapter = new SuoLueAdapter1(this, imgEntities, new Onclick() {
            @Override
            public void click(int p, int count, boolean isadd, ImgEntity imgEntity) {
                if (isadd) {
                    BiaoZhuActivity.this.imgEntity = imgEntity;
                    Glide.with(BiaoZhuActivity.this)
                            .asBitmap()
                            .load(TimeUtils.getWlanIp() + "/public/" + imgEntity
                                    .getSrcpath() + imgEntity
                                    .getSrcname())
                            .into(imageView);
                    title.setText(imgEntity
                            .getCdate());
                    imgEntityArrayList.add(imgEntity);
                } else {
                    imgEntityArrayList.remove(imgEntity);
                }
                Log.d("ccnb", imgEntityArrayList.size() + "");
//            if ( count == 0) {
//                    ss.setVisibility(View.VISIBLE);
//                    ss1.setVisibility(View.GONE);
//                } else {
//                    ss1.setVisibility(View.VISIBLE);
//                    ss.setVisibility(View.GONE);
//                }
            }
        });
//        Glide.with(this)
//                .asBitmap()
//                .load(ApiConstant.BOX_BASE_URL + "/public/" + imgEntities.get(0)
//                        .getSrcpath() + imgEntities.get(0)
//                        .getSrcname())
//                .into(imageView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
