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
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.closet_f.R;
import com.exam.closet_f.util.OkHttpUtils;
import com.mob.MobSDK;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity {
    Toolbar toolbar;
    String url,urlc,account,password,repassword,code,way,wayy;
    private LinearLayout ll_all;
    private Button btn_register,btn_sureCode;
    private EditText etId,etPwd,etRepwd,etCode;
    private CheckBox checkBox;
    private TextView tvCode,tvCodere;
    EventHandler eventHandler;
    int checkBoxstate,telResult;
    String patternTel,patternCode;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        SMSSDK.registerEventHandler(eventHandler);


        initData();
        url = getResources().getString(R.string.url_register);
        urlc = getResources().getString(R.string.url_registercode);
        patternTel = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        patternCode ="^\\d{6}$";//验证码为6位数字



        //发送验证码
        tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = etId.getText().toString();
//                if(Pattern.compile(patternTel).matcher(account).matches()){
//                if(account.matches(patternTel)){
//                if(isTel(account)){
//                    Log.i("------number","符合格式");//电话号码符合格式才能发送验证码
//                    tvCode.setEnabled(true);
//                    tvCode.setTextColor(getResources().getColor(R.color.yellow));

//                在使用SDK功能之前调用即可  发送即表示同意隐私协议
                    MobSDK.submitPolicyGrantResult(true, null);

                //注册一个事件回调监听，用于处理SMSSDK接口请求的结果
                     SMSSDK.registerEventHandler(eh);

//              发送验证码   handler判断号码格式和是否发送
//                    SMSSDK.getVerificationCode("86",account,messageHandler);
                judgeNumberIfRegister(account);
//                把发送验证码包含在内
//                SMSSDK.getVerificationCode("86",account);
//                }else{
//                    tvCodere.setText("请输入正确号码");
//                    Log.i("-------","请输入正确号码");
//                }
            }
        });

//        验证码验证
        btn_sureCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = etCode.getText().toString();
                if("".equals(code)){
                    tvCodere.setText("验证码不能为空");
//                   Toast.makeText(getApplication(),"",Toast.LENGTH_LONG).show();
                }else if(Pattern.compile(patternCode).matcher(code).matches()){//是六位数字则进行验证
                    SMSSDK.submitVerificationCode("86",account,code);
                    tvCodere.setText("验证成功");
//                    Toast.makeText(getApplicationContext(),"已验证",Toast.LENGTH_LONG).show();
                }else {
                    tvCodere.setText("验证失败");
//                    Toast.makeText(getApplicationContext(),"验证失败",Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = etId.getText().toString();
                code = etCode.getText().toString();
                password = etPwd.getText().toString();
                repassword = etRepwd.getText().toString();

                String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
                if(Pattern.compile(pattern).matcher(account).matches()){
                    way = "email";
                }else{ way = "tel"; }

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Toast.makeText(getApplication(),"已勾选",Toast.LENGTH_SHORT).show();
                            checkBoxstate = 1;
                        }else{
                            Toast.makeText(getApplication(),"取消勾选",Toast.LENGTH_SHORT).show();
                            checkBoxstate = 0;}
                    }
                });

                //注册信息检验
                Log.i("---tip","注册");
                if(account != null && password != null && repassword != null && code != null && checkBoxstate == 1){
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
                }else if(checkBoxstate == 0){
                    Toast.makeText(getApplication(),"请勾选协议",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplication(),"请完成全部填写",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//服务端返回注册结果
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


    private void judgeNumberIfRegister(String tel){
        //结合messageHandler方法，在数据库中验证是否已注册，未注册即可发
        final HashMap<String, String> params = new HashMap<String, String>();
        params.put("account", tel);
        Log.i("=======",tel);
        params.put("way", "tel");
        Log.i("---tip", "params");
        try {
            OkHttpUtils.doPostSync(urlc, params, myHandlercode);
            Log.i("---tip", "okHttpUtils");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (telResult == 1) {
            SMSSDK.getVerificationCode("86",account);
            Toast.makeText(getApplication(), "已发送验证码", Toast.LENGTH_LONG).show();
            tvCode.setTextColor(getResources().getColor(R.color.yellow));

        } else if (telResult == 0) {
            Toast.makeText(getApplication(), "该号码已注册", Toast.LENGTH_LONG).show();
            tvCodere.setText("该号码已注册");
        }
    }

    OnSendMessageHandler messageHandler = new OnSendMessageHandler() {
        /**
         * 此方法在发送验证短信前被调用，传入参数为接收者号码
         * 返回true表示此号码无须实际接收短信
         */
        @Override
        public boolean onSendMessage(String country, String phone) {
//            String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
            String pattern = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
//            return Pattern.compile(pattern).matcher(account).matches();
            Boolean result = true;
            if (Pattern.compile(pattern).matcher(account).matches()) {
                final HashMap<String, String> params = new HashMap<String, String>();
                params.put("account", account);
                params.put("way", "tel");
                Log.i("---tip", "params");
                try {
//                        OkHttpUtils.doGetSync(url,params,PostmyHandler);
                    OkHttpUtils.doPostSync(urlc, params, myHandlercode);
                    Log.i("---tip", "okHttpUtils");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (telResult == 1) {
                    Toast.makeText(getApplication(), "已发送验证码", Toast.LENGTH_LONG).show();
                    result = false;
                } else if (telResult == 0) {
                    Toast.makeText(getApplication(), "该号码已注册", Toast.LENGTH_LONG).show();
                }
            }
            return result;
        }
    };

//数据库获得该号码是否注册结果
    @SuppressLint("HandlerLeak")
    private Handler myHandlercode = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String response = msg.obj.toString();
            Log.i("---response", response);
            if (msg.obj == null) {
                Toast.makeText(getApplication(), "网络请求失败，请稍后再试！", Toast.LENGTH_SHORT).show();
            } else if ("telExist".equals(response)) {
                telResult = 0;
                Log.i("------------code","该号码已注册");
//              Toast.makeText(getApplication(), "", Toast.LENGTH_SHORT).show();
            }else if ("telAllow".equals(response)){//RegisterAllow
                telResult = 1;
                Log.i("------------code","该号码可注册");
//              oast.makeText(getApplication(), "该号码可注册", Toast.LENGTH_SHORT).show();
            }else
                telResult = 2;
                Log.i("------------code","WRONG");
        }
    };

//    注册一个事件回调监听，用于处理SMSSDK接口请求的结果
    Handler msHandler = new Handler();
    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
//            // TODO 此处不可直接处理UI线程，处理后续操作需传到主线程中操作
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            msHandler.sendMessage(msg);
        }

    };

    // 使用完EventHandler需注销，否则可能出现内存泄漏
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

//手机号判断 true为通过验证
    public static boolean isTel(String str) throws PatternSyntaxException {
        if (str == null) {
            return false;
        }
        if (str.length() != 11) {
            return false;
        }
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @SuppressLint("ClickableViewAccessibility")
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
        btn_sureCode = findViewById(R.id.btn_sure_code);
        etId = findViewById(R.id.et_id);
        etPwd = findViewById(R.id.et_pwd);
        etRepwd = findViewById(R.id.et_repwd);
        checkBox = findViewById(R.id.checkbox);
        tvCode = findViewById(R.id.tv_code);
        tvCodere = findViewById(R.id.tv_codere);
        etCode = findViewById(R.id.et_code);

//        点击焦点 让键盘消失
        ll_all = findViewById(R.id.ll_all);
        ll_all.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ll_all.setFocusable(true);
                ll_all.setFocusableInTouchMode(true);
                ll_all.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
    }

}

