package com.example.azureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class LoginWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_web);
        WebView loginWebView = findViewById(R.id.wv_login);
        loginWebView.getSettings().setJavaScriptEnabled(true);
        loginWebView.loadUrl("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=13&rver=7.3.6963.0&wp=MBI_SSL&wreply=https%3a%2f%2fwww.microsoft.com%2fzh-cn%2f%3flc%3d2052&lc=2052&id=74335&aadredir=1");
    }
}