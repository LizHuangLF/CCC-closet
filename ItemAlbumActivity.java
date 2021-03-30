package com.exam.closet_f.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.closet_f.R;

public class ItemAlbumActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView title,choosePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_album);
        //        添加自定义toolbar
        LinearLayout toolbarm=findViewById(R.id.toolbarm);
        View barView = LayoutInflater.from(this).inflate(R.layout.toolbar_m,toolbarm);
//        LayoutInflater.from(this).inflate(R.layout.toolbar_m,toolbarm,false);取消
//        toolbarm.addView(barView);
        toolbarm.setBackgroundColor(getResources().getColor(R.color.colorXRenYellow));
        initData(barView);

    }

    private void initData(View barView){
        ivBack = barView.findViewById(R.id.iv_back);
        title = barView.findViewById(R.id.tv_title);
        title.setText("全部单品");
        choosePic = barView.findViewById(R.id.tv_over);
        choosePic.setText("选择");
        choosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"多选照片直接进入搭配",Toast.LENGTH_LONG).show();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemAlbumActivity.this,CCCActivity.class));
                finish();
            }
        });

    }

}
