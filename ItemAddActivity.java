package com.exam.closet_f.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.closet_f.R;

public class ItemAddActivity extends AppCompatActivity {
//    BarActivity bar;
//    View barView;
    private TextView title,over;
    private Spinner spColor;
    private ImageView ivPic,ivColor,ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);
//        添加自定义toolbar
        LinearLayout toolbarm=findViewById(R.id.toolbarm);
        View barView = LayoutInflater.from(this).inflate(R.layout.toolbar_m,toolbarm);
//        LayoutInflater.from(this).inflate(R.layout.toolbar_m,toolbarm,false);取消
        toolbarm.setBackgroundColor(getResources().getColor(R.color.colorXRenYellow));
        initData(barView);



    }

    private void initData(View barView){
        ivBack = barView.findViewById(R.id.iv_back);
        title = barView.findViewById(R.id.tv_title);
        title.setText("添加单品");
        over = barView.findViewById(R.id.tv_over);
        over.setText("完成");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemAddActivity.this,CCCActivity.class));
                finish();
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //验证是否可以进行提交
                //1 图片是否为空
//                2其余数据是否错误
                Toast.makeText(getApplication(),"完成",Toast.LENGTH_SHORT).show();
//                返回该衣服类别的全部图
            }
        });

        ivColor = findViewById(R.id.iv_color);
        spColor = findViewById(R.id.sp_color);showSpcolor();

        ivPic = findViewById(R.id.iv_pic);
        ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                https://blog.csdn.net/qq_46020030/article/details/114260916?ops_request_misc=&request_id=&biz_id=102&utm_term=android%20%E7%82%B9%E5%87%BB%E5%9B%BE%E7%89%87%E8%8E%B7%E5%8F%96%E9%A2%9C%E8%89%B2&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-1-114260916.pc_search_result_cache
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    private void showSpcolor(){
        spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String[] color = getResources().getStringArray(R.array.colo);
                Toast.makeText(ItemAddActivity.this,"点击"+color[pos],Toast.LENGTH_SHORT).show();
//                if("0".equals(color[pos])){//红
//                    tvColor.setBackgroundResource(R.color.red);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

//    private void showToolbar(){


//        toolbar.setTitle("添加单品");
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ItemAddActivity.this,CCCActivity.class));
//                finish();
//            }
//        });
//        toolbar.setBackgroundResource(R.color.colorXinYellow);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.m_wan:
//                        Toast.makeText(ItemAddActivity.this, "完成添加", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.m_clear:
//                        Toast.makeText(ItemAddActivity.this, "清除", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//        });
//    }

}


