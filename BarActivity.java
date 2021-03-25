package com.exam.closet_f.util;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.exam.closet_f.R;

public class BarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bar);
    }

    public void setBack(String title){
        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.setTitle(title);//为标题栏设置标题，即给ActionBar设置标题
            bar.setDisplayHomeAsUpEnabled(true);//ActionBar加一个返回图标
           bar.setDisplayShowHomeEnabled(false);//不显示当前程序的图标
        }
    }
}
