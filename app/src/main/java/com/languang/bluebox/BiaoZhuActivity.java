package com.languang.bluebox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.entity.ImgEntity;

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
    List<ImgEntity> imgEntities;
    List<ImgEntity> imgEntityArrayList = new ArrayList<>();
    ImgEntity imgEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biao_zhu);
        recyclerView = findViewById(R.id.recycler_view);
        meihua = findViewById(R.id.meihua);
        piliang = findViewById(R.id.piliang);
        biaozhu = findViewById(R.id.biaozhu);
        imageView = findViewById(R.id.image);
        title = findViewById(R.id.title);
        ss = findViewById(R.id.ss);
        ss1 = findViewById(R.id.ss1);
        piliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BiaoZhuActivity.this, AddPic1Activity.class);
                intent.putExtra("spe", new Gson().toJson(imgEntityArrayList));
                startActivity(intent);
                finish();
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
        meihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(BiaoZhuActivity.this, CropImageActivity.class);
//                startActivity(intent);
//                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_SAVE_PATH, saveToPath);
            }
        });
        imgEntities = new Gson().fromJson(getIntent().getStringExtra("json"), new TypeToken<List<ImgEntity>>() {
        }.getType());
//        title.setText(imgEntities.get(0)
//                .getCdate());
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
                if (count == 1 | count == 0) {
                    ss.setVisibility(View.VISIBLE);
                    ss1.setVisibility(View.GONE);
                } else {
                    ss1.setVisibility(View.VISIBLE);
                    ss.setVisibility(View.GONE);
                }
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
