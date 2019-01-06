package com.languang.bluebox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LookForPasswordActivity extends AppCompatActivity {
    EditText old;
    EditText newP;
    EditText confirmNewP;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_for_password);
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
