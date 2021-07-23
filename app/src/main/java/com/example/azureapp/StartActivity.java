package com.example.azureapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-08 17:12
 **/
public class StartActivity extends AppCompatActivity {

    //登录按钮
    private Button loginBtn;
    //转发按钮
    private TextView forwardTv;

    /**
     * 初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //设置登录按钮
        loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, VerifyWebActivity.class);
                startActivity(intent);
            }
        });

        //设置转发
        forwardTv = findViewById(R.id.tv_forward);
        forwardTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                //跳转
                Uri privacy_url = Uri.parse("https://privacy.microsoft.com");
                intent.setData(privacy_url);
                startActivity(intent);
            }
        });
    }
}