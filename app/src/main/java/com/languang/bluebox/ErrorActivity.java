package com.languang.bluebox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ErrorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roor);
        TextView req=findViewById(R.id.req);
        TextView res=findViewById(R.id.res);
        req.setText(getIntent().getStringExtra("req"));
        res.setText(getIntent().getStringExtra("res"));
    }
}
