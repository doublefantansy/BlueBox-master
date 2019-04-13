package com.languang.bluebox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xuexiang.xui.widget.actionbar.TitleBar;

public class LookForPasswordActivity extends AppCompatActivity {
    EditText old;
    EditText newP;
    EditText confirmNewP;
    Button button;
    TitleBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_for_password);
        bar = findViewById(R.id.bar);
        bar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        old = findViewById(R.id.pwd_et);
        newP = findViewById(R.id.pwd_et1);
        confirmNewP = findViewById(R.id.pwd_et2);
        button = findViewById(R.id.register_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
