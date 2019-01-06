package com.languang.bluebox;

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
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.ImgEntity;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.io.File;
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
    List<ImgEntity> imgEntities = new ArrayList<>();
    List<ImgEntity> choose = new ArrayList<>();
    List<File> files;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_chu);
        title = findViewById(R.id.title);
        imageView = findViewById(R.id.image);
        recyclerView = findViewById(R.id.recycler_view);
        daochu = findViewById(R.id.daochu);
        daochu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Map<String, Object> map = new HashMap<>();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            final Context context = getApplicationContext();
//                            for (ImgEntity imgEntity : choose) {
//                                String url = ApiConstant.BOX_BASE_URL + "/public/" + imgEntity
//                                        .getSrcpath() + imgEntity
//                                        .getSrcname();
//                                FutureTarget<File> target = Glide.with(context)
//                                        .asFile()
//                                        .load(url)
//                                        .submit();
//                                final File imageFile = target.get();
////                                final Map<String,Object> map1=new HashMap<>();
////                                map1.put("files",imageFile);
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
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
//                                    }
//                                });
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
////                    OkHttpUtils.getInstance()
////                            .uploadFile(DaoChuActivity.this, TimeUtils.getWlanIp() + "/fileout", ApiConstant.BOX_BASE_URL + "/public/" + imgEntity
////                                    .getSrcpath() + imgEntity
////                                    .getSrcname(), new OkHttpCallBack() {
////                                @Override
////                                public void onSucceed(String requestUrl, String response) {
////                                    Log.d("ccnb", response);
////                                }
////
////                                @Override
////                                public void onFailed() {
////                                    Log.d("ccnb", "fil");
////                                }
////                            });
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
                    title.setText(imgEntity
                            .getCdate());
                    choose.add(imgEntity);
                } else {
                    choose.remove(imgEntity);
                }
                Log.d("ccnb", choose.size() + "");
            }
        });
        recyclerView.setAdapter(adapter);
        imgEntities.addAll((List<ImgEntity>) new
                Gson().
                fromJson(getIntent().
                        getStringExtra("spe"), new TypeToken<List<ImgEntity>>() {
                }.
                        getType()));
        adapter.notifyDataSetChanged();
    }
}
