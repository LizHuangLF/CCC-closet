package com.exam.closet_f.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.exam.closet_f.R;
import com.exam.closet_f.fragment.dialogFrag;
import com.exam.closet_f.fragment.inFragment;
import com.exam.closet_f.fragment.mineFragment;
import com.exam.closet_f.fragment.modelFragment;
import com.exam.closet_f.fragment.recordFragment;
import com.exam.closet_f.util.FragCallBack;

import java.util.Objects;

import static com.exam.closet_f.R.drawable.ic_inc;

//https://blog.csdn.net/zl18603543572/article/details/108466070?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522161590347516780255243996%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=161590347516780255243996&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-108466070.pc_search_result_before_js&utm_term=android+fragment%E4%B8%8Eactivity%E4%BC%A0%E5%80%BC

public class CCCActivity extends AppCompatActivity implements FragCallBack {
    private RadioGroup rg;
    private RadioButton rb;
    private RadioButton rbIn,rbRecod,rbModel,rbMine;
    String uname = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccc);
        initData();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            uname = bundle.getString("uname");
        }

//        fragment 切换
        int id = getIntent().getIntExtra("id",0);
        if( id==0 ){//分类
            setIndexSelected(0);  rbIn.setChecked(true);
        }else if( id==1 ){//记录
            setIndexSelected(1);  rbRecod.setChecked(true);
        }else if( id==2 ){//心选
            setIndexSelected(2);  rbModel.setChecked(true);
        }else {//我的
            setIndexSelected(3);  rbMine.setChecked(true);
        }
    }

    /*
    开启事务，加载fragment布局
     */
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.commit();
    }

    /*
   通过index判断当前加载哪个界面
    */
    public void setIndexSelected(int index){
        switch (index){
            case 0:
                showInFragment();  break;
            case 1:
                showRecordFragmenr();  break;
            case 2:
                showModelFragment();  break;
            case 3:
                showMineFragment();  break;
            default: break;
        }
    }

    private void initData(){
        rbIn = findViewById(R.id.bo_in);
        rbRecod = findViewById(R.id.bo_record);
        rbModel = findViewById(R.id.bo_model);
        rbMine = findViewById(R.id.bo_mine);
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0;i<rg.getChildCount();i++){
                    rb = (RadioButton)group.getChildAt(i);
                    if(rb.isChecked()){
                        setIndexSelected(i);
                        break;
                    }
                }
            }
        });
    }

    private void showInFragment(){
        //创建 Fragment 实例
        inFragment in = new inFragment();
        //构建 Bundle
        Bundle bundleIN = new Bundle();
        //设置数据
        bundleIN.putString("title", "传递到的数据");
        //绑定 Fragment
        in.setArguments(bundleIN);
        changeFragment(in);
    }

    private void showRecordFragmenr(){
        recordFragment record = new recordFragment();
//        recordFragment record = recordFragment.newInstance("测试数据");
        changeFragment(record);

    }

    private void showModelFragment(){
//        dialogFrag frag = new dialogFrag();
//        frag.show(getSupportFragmentManager(),"show");
        modelFragment model = new modelFragment();
        changeFragment(model);
    }

    private void showMineFragment(){
        //创建 Fragment 实例
        mineFragment mine = new mineFragment();
        //构建 Bundle
        Bundle bundleMine = new Bundle();
        //设置数据
        bundleMine.putString("uname", uname);
        //绑定 Fragment
        mine.setArguments(bundleMine);
        changeFragment(mine);
    }

    @Override
    public void test(int flag) {
        Log.i("---------",""+flag);
    }
//    private void showRecordFragmenr(){
//        //创建 Fragment 实例
//        // 通过静态方法来创建并绑定数据
//        recordFragment record = recordFragment.newInstance("测试数据");
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, record);
//        fragmentTransaction.commit();
//    }
}
