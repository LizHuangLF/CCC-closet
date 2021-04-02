package com.exam.closet_f.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.closet_f.R;
import com.exam.closet_f.util.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private TextView tv;
    private EditText etId,etPwd;
    private TextView tvRepwd,tvRegi;
    private Button btnLogin;
    private String url,account,password,way;
    private String tip="";
    private String param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        tv = findViewById(R.id.tv);
//        Bundle bundle=this.getIntent().getExtras();
//        String data= null;
//        if (bundle != null) {
//            data = bundle.getString("key");
//        }
//        tv.setText(data);
        initData();
        url = getResources().getString(R.string.url_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = etId.getText().toString();
                password = etPwd.getText().toString();
//                Pattern p = Pattern.compile("/^\\d+$/g");
//                if(Pattern.compile("[@]").matcher(account).matches()){
                String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
                if(Pattern.compile(pattern).matcher(account).matches()){
                    way = "email";
                }else{ way = "tel";}

                //登录信息检验
                Log.i("---tip","登录");
                if(account != null && password != null){
                    final HashMap<String,String> params=new HashMap<String, String>();
                    params.put("account",account);
                    params.put("password",password);
                    params.put("way",way);
                    Log.i("---tip","params");
                    try{
                        OkHttpUtils.doPostSync(url,params,myHandler);
                        Log.i("---tip","okHttpUtils");
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(LoginActivity.this, "网络请求失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            } else if ("id||pwd WRONG".equals(response)) {
                Toast.makeText(LoginActivity.this, "登录失败，账号或密码错误", Toast.LENGTH_SHORT).show();
//            } else if ("LoginSUCC".equals(response)) {
            }else{//
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
//                Bundle bundle = new Bundle();
//                bundle.putString("uname",response);
                Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.putExtra("username",response);//传值
                intent.setClass(LoginActivity.this, CCCActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    private void initData() {
        etId = findViewById(R.id.et_id);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
        tvRepwd = findViewById(R.id.tv_repwd);
        tvRegi = findViewById(R.id.tv_regi);

        tvRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

}
