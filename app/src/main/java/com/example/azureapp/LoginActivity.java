package com.example.azureapp;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText userEt;
    private EditText pswEt;
    private TextView userTipTv;
    private TextView pswTipTv;
    private String userTip;
    private String pswTip;
    private Button loginBtn;
    private TextView forgetTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userTip = "请输入有效的电子邮件地址、电话号码或Skype用户名。";
        pswTip = "你的密码不正确。如果你不记得记得密码，请立即进行重置。";
        userEt = findViewById(R.id.et_user);
        pswEt = findViewById(R.id.et_psw);
        userTipTv = findViewById(R.id.tv_user_tip);
        pswTipTv = findViewById(R.id.tv_psw_tip);
        forgetTv = findViewById(R.id.tv_forget);
        //下划线
        forgetTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        forgetTv.getPaint().setAntiAlias(true);
        forgetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri privacy_url = Uri.parse("https://account.live.com/acsr");
                intent.setData(privacy_url);
                startActivity(intent);
            }
        });
        loginBtn = findViewById(R.id.btn_login_2);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
    }

    private void logIn(){
        Boolean userValid = false, pswValid = false;
        String userName = userEt.getText().toString();
        String psw = pswEt.getText().toString();
        /**
         * 验证用户名和密码是否正确
         */
        if(!userValid){
            //提示用户名错误
            userTipTv.setText(userTip);
            userTipTv.setTextSize(15);
        }
        if(!pswValid){
            //提示密码错误
            pswTipTv.setText(pswTip);
            pswTipTv.setTextSize(15);
        }
        /**
         * 验证登录是否成功
         * 成功则跳转
         */
    }
}