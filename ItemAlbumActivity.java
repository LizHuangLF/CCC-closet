package com.exam.closet_f.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.closet_f.R;
import com.exam.closet_f.adapter.albumAdapter;
import com.exam.closet_f.bean.ItemPic;

import java.util.ArrayList;
import java.util.List;

public class ItemAlbumActivity extends AppCompatActivity {
    private ImageView ivBack;
    private TextView title,choosePic;
    GridView grid;
    private int[] itempics= new int[]{R.drawable.up_vest,R.drawable.up_coat_black,R.drawable.up_coat_brown,
            R.drawable.up_coat_cow,R.drawable.up_shirt_wen,R.drawable.up_shirt_white,R.drawable.up_shirt_white_f,
            R.drawable.bo_cq_white,R.drawable.up_shirt_black,R.drawable.up_shirt_black_v,R.drawable.up_shirt_brown,
            R.drawable.up_short_black,R.drawable.up_short_blue,R.drawable.up_short_yellow,R.drawable.up_shirt_xin_o,
            R.drawable.up_short_ge,R.drawable.up_swear_ge,R.drawable.up_coat_ge,R.drawable.up_shirt_wen_d};

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

        grid = findViewById(R.id.mGridView);
        List<ItemPic> picList = new ArrayList<>();
        for(int i = 0; i < itempics.length;i++){
            ItemPic itemPic = new ItemPic();
            itemPic.setPic(itempics[i]);
            picList.add(itemPic);
        }
        albumAdapter adapter = new albumAdapter(this,picList);
        grid.setAdapter(adapter);


    }


}
