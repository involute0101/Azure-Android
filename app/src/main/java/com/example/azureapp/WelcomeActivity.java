package com.example.azureapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-08 19:29
 **/
public class WelcomeActivity extends AppCompatActivity {

    /**
     * 跳转
     */
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            Intent intent = new Intent(WelcomeActivity.this, com.example.azureapp.StartActivity.class);
            startActivity(intent);
            WelcomeActivity.this.finish();
        }
    };

    /**
     * 初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(0,3000);
    }
}