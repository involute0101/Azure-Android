package com.example.azureapp;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private WebView loginWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginWebView = findViewById(R.id.wv_login);
        loginWebView.getSettings().setJavaScriptEnabled(true);
        loginWebView.loadUrl("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=13&rver=7.3.6963.0&wp=MBI_SSL&wreply=https%3a%2f%2fwww.microsoft.com%2fzh-cn%2f%3flc%3d2052&lc=2052&id=74335&aadredir=1");
    }
}