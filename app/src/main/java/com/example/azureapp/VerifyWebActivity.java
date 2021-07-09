package com.example.azureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-09 20:39
 **/
public class VerifyWebActivity extends AppCompatActivity {

    WebView verifyWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_web);
        verifyWebView = findViewById(R.id.wv_verify);
        verifyWebView.getSettings().setJavaScriptEnabled(true);
        verifyWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        verifyWebView.loadUrl("https://microsoft.com/devicelogin");
    }
}