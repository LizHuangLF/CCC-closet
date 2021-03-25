package com.exam.closet_f.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.exam.closet_f.R;

public class RegisterActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initData();

    }

    private void initData(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("注册");
        toolbar.setBackgroundResource(R.color.colorJSkyBlue);
    }
}
