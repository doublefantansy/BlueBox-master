package com.languang.bluebox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ResponseMessage;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPic1Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    SuoLueAdapter1 adapter;
    TextView mTextView;
    ImageView mImageView;
    Button button;
    Button commit;
    LinearLayout linearLayout;
    AudioRecoderUtils mAudioRecoderUtils;
    File file;
    int count = 0;
    private MaterialDialog dialog;
    MyCallBack myCallBack;
    In in;
    int count2 = 0;
    String voiceUuid;
    List<String> strings;
    EditText desc;
    EditText private1;
    EditText title;
    EditText meta;
    EditText location;
    EditText camera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpic);
        commit = findViewById(R.id.commit);
        dialog = new MaterialDialog.Builder(this).title("正在上传中")
                .titleGravity(GravityEnum.CENTER)
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .build();
        button = findViewById(R.id.button);
        linearLayout = findViewById(R.id.rl);
        recyclerView = findViewById(R.id.suoliue);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
//        in = PopView.in;
        final List<ImgEntity> mediaBeans = new Gson().fromJson(getIntent().getStringExtra("spe"), new TypeToken<List<ImgEntity>>() {
        }.getType());
        strings = new ArrayList<>();
        for (int i = 0; i < mediaBeans.size(); i++) {
            strings.add(mediaBeans.get(i)
                    .getUuid());
        }
        adapter = new SuoLueAdapter1(this, mediaBeans, null);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        final View view = View.inflate(this, R.layout.layout_microphone, null);
        final PopupWindowFactory mPop = new PopupWindowFactory(this, view);
        //PopupWindow布局文件里面的控件
        mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        mTextView = (TextView) view.findViewById(R.id.tv_recording_time);
        mAudioRecoderUtils = new AudioRecoderUtils();
        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                //根据分贝值来设置录音时话筒图标的上下波动，下面有讲解
                Log.d("ccnb", TimeUtils.long2String(time));
                mImageView.getDrawable()
                        .setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(TimeUtils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                Toast.makeText(AddPic1Activity.this, "录音保存在：" + filePath, Toast.LENGTH_SHORT)
                        .show();
                file = new File(filePath);
                mTextView.setText(TimeUtils.long2String(0));
            }
        });
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPop.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
                        button.setText("松开保存");
                        mAudioRecoderUtils.startRecord();
                        break;
                    case MotionEvent.ACTION_UP:
                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
//                        mAudioRecoderUtils.cancelRecord();    //取消录音（不保存录音文件）
                        mPop.dismiss();
                        button.setText("按住说话");
                        break;
                }
                return true;
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ccnb", file.getAbsolutePath());
                OkHttpUtils.getInstance()
                        .uploadFile(AddPic1Activity.this, TimeUtils.getWlanIp() + "/upload", file.getPath(), new OkHttpCallBack() {
                            @Override
                            public void onSucceed(String requestUrl, String response) {
                                ResponseMessage<SpeVoiceBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<SpeVoiceBean>>() {
                                }.getType());
                                voiceUuid = responseMessage.getData().vocuuid;
                                Map<String, Object> map = new HashMap<>();
//                                for (int i = 0; i < strings.length; i++) {
                                for (String string : strings) {
                                    map.put("files", string);
//                                }
                                    map.put("vocuuid", voiceUuid);
                                    map.put("desc", desc.getText()
                                            .toString());
                                    map.put("private", "11");
                                    map.put("title", title.getText()
                                            .toString());
                                    map.put("meta", meta.getText()
                                            .toString());
                                    map.put("location", location.getText()
                                            .toString());
                                    map.put("camera", camera.getText()
                                            .toString());
                                    OkHttpUtils.getInstance()
                                            .okPost(AddPic1Activity.this, TimeUtils.getWlanIp()+"tagfiles", map, new OkHttpCallBack() {
                                                @Override
                                                public void onSucceed(String requestUrl, String response) {
                                                    Log.d("ccnb", response);
                                                    count2++;
                                                    if (count2 == strings.size()) {
                                                        finish();
                                                    }
                                                }

                                                @Override
                                                public void onFailed() {
                                                    Log.d("ccnb", "ccnb");
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
            }
        });
//        Log.d("ccnb",getIntent().getStringExtra("spe")+"");
    }
}