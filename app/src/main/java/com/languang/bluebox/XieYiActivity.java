package com.languang.bluebox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class XieYiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xie_yi);
        TextView textView = findViewById(R.id.content);
//        OkHttpUtils.getInstance().okPost(this,);
    }
}
