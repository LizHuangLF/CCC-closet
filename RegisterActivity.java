package com.exam.closet_f.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.closet_f.R;
import com.exam.closet_f.util.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Toolbar toolbar;
    String url,account,password,repassword,code,way,wayy;
    private Button btn_register;
    private EditText etId,etPwd,etRepwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initData();
        url = getResources().getString(R.string.url_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = etId.getText().toString();
                password = etPwd.getText().toString();
                repassword = etRepwd.getText().toString();

                String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
                if(Pattern.compile(pattern).matcher(account).matches()){
                    way = "email";
                }else{ way = "tel"; }

                //注册信息检验
                Log.i("---tip","注册");
                if(account != null && password != null && repassword != null && code != null){
                    final HashMap<String,String> params=new HashMap<String, String>();
                    params.put("account",account);
                    params.put("password",password);
                    params.put("way",way);
                    Log.i("---tip","params");
                    try{
//                        OkHttpUtils.doGetSync(url,params,PostmyHandler);
                        OkHttpUtils.doPostSync(url,params,myHandler);
                        Log.i("---tip","okHttpUtils");

                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getApplication(),"请完成全部填写",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @SuppressLint("HandlerLeak")
    final Handler myHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String response = msg.obj.toString();
            Log.i("---response", response);
            if (msg.obj == null) {
                Toast.makeText(getApplication(), "网络请求失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            } else if ("telExist".equals(response)) {
                Toast.makeText(getApplication(), "该号码已注册", Toast.LENGTH_SHORT).show();
            }else if("emExist".equals(response)){
                Toast.makeText(getApplication(), "该邮箱已注册", Toast.LENGTH_SHORT).show();
            }else{//RegisterSUCC
                Toast.makeText(getApplication(), "注册成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        }
    };


    private void initData(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("注册");
        toolbar.setBackgroundResource(R.color.colorJSkyBlue);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        btn_register = findViewById(R.id.btn_register);
        etId = findViewById(R.id.et_id);
        etPwd = findViewById(R.id.et_pwd);
        etRepwd = findViewById(R.id.et_repwd);

    }
}
