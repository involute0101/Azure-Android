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

    private Button loginBtn;
    private TextView forwardTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, com.example.azureapp.LoginActivity.class);
                startActivity(intent);
            }
        });

        forwardTv = findViewById(R.id.tv_forward);
        forwardTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri privacy_url = Uri.parse("https://privacy.microsoft.com");
                intent.setData(privacy_url);
                startActivity(intent);
            }
        });
    }
}