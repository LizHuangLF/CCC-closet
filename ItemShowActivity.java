package com.exam.closet_f.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.exam.closet_f.R;
import com.exam.closet_f.adapter.albumAdapter;
import com.exam.closet_f.bean.ItemPic;

import java.util.ArrayList;
import java.util.List;

public class ItemShowActivity extends AppCompatActivity {
    GridView gridShort,gridShirt,gridCoat,gridCaftan;
    Toolbar toolbar;
    String str;
    private int[] itempicsCoat= new int[]{R.drawable.up_coat_black,R.drawable.up_coat_brown,R.drawable.up_coat_cow,
            R.drawable.up_coat_ge,R.drawable.up_shirt_white,R.drawable.up_shirt_white_f};

    private int[] itempicsShort = new int[]{R.drawable.up_short_black,R.drawable.up_short_blue,R.drawable.up_short_yellow};
    private int[] itempicsCaftan = new int[]{R.drawable.up_shirt_wen,R.drawable.up_shirt_brown,R.drawable.up_swear_ge};
    private int[] itempicsShirt = new int[]{R.drawable.up_short_ge,R.drawable.up_shirt_black,R.drawable.up_shirt_black_v,
            R.drawable.up_shirt_xin_o,R.drawable.up_shirt_wen_d};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_show);
        initData();
    }

    private void initData(){
        toolbar = findViewById(R.id.toolbar);
        showBar();

        gridShirt = findViewById(R.id.grid_shirt);
        List<ItemPic> picList1 = new ArrayList<>();
        for(int i = 0; i < itempicsShirt.length;i++){
            ItemPic itemPic = new ItemPic();
            itemPic.setPic(itempicsShirt[i]);
            picList1.add(itemPic);
        }
        albumAdapter adapter1 = new albumAdapter(this,picList1);
        gridShirt.setAdapter(adapter1);

        gridShort = findViewById(R.id.grid_short);
        List<ItemPic> picList2 = new ArrayList<>();
        for(int i = 0; i < itempicsShort.length;i++){
            ItemPic itemPic = new ItemPic();
            itemPic.setPic(itempicsShort[i]);
            picList2.add(itemPic);
        }
        albumAdapter adapter2 = new albumAdapter(this,picList2);
        gridShort.setAdapter(adapter2);

        gridCaftan = findViewById(R.id.grid_caftan);
        List<ItemPic> picList3 = new ArrayList<>();
        for(int i = 0; i < itempicsCaftan.length;i++){
            ItemPic itemPic = new ItemPic();
            itemPic.setPic(itempicsCaftan[i]);
            picList3.add(itemPic);
        }
        albumAdapter adapter3 = new albumAdapter(this,picList3);
        gridCaftan.setAdapter(adapter3);

        gridCoat = findViewById(R.id.grid_coat);
        List<ItemPic> picList4 = new ArrayList<>();
        for(int i = 0; i < itempicsCoat.length;i++){
            ItemPic itemPic = new ItemPic();
            itemPic.setPic(itempicsCoat[i]);
            picList4.add(itemPic);
        }
        albumAdapter adapter4 = new albumAdapter(this,picList4);
        gridCoat.setAdapter(adapter4);
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

