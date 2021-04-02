package com.exam.closet_f.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.exam.closet_f.R;

public class ItemShowActivity extends AppCompatActivity {
    Toolbar toolbar;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_show);

        initData();
    }

    private void initData(){
        toolbar = findViewById(R.id.toolbar);
        showBar();
    }

    private void showBar(){
        toolbar.setTitle(getItem(str));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemShowActivity.this,CCCActivity.class));
                finish();
            }
        });
//        toolbar.setBackgroundResource(R.color.colorXinYellow);
    }

    private String getItem(String str){
        int id = getIntent().getIntExtra("id",0);
        switch (id){
            case 0: str = "上装"; break;
            case 1: str = "下装"; break;
            case 2: str = "鞋袜"; break;
            case 3: str = "包帽"; break;
            case 4: str = "套装"; break;
            case 5: str = "其他"; break;
            default:break;
        }
         return str;
    }
}
