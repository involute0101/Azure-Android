package com.example.azureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-09 20:39
 **/
public class VerifyWebActivity extends AppCompatActivity {

    WebView verifyWebView;
    GradientColorTextView mTvCode;
    boolean isTransform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_web);
        isTransform = true;
        verifyWebView = findViewById(R.id.wv_verify);
        mTvCode = findViewById(R.id.tv_verify_code);
        verifyWebView.getSettings().setJavaScriptEnabled(true);
        verifyWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        verifyWebView.loadUrl("https://microsoft.com/devicelogin");
        getCode();
        verifyWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                if(view.getUrl().equals("https://login.microsoftonline.com/appverify") && isTransform==true)
                {
                    isTransform = false;
                    Intent intent = new Intent(VerifyWebActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getCode(){
        Thread thread = new Thread(new Runnable() {
            JSONObject object;
            @Override
            public void run() {
                //通过资源组和虚拟机名称获得详情信息
                String url = "http://20.89.169.250:8888/getCode";
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        object = (JSONObject) JSONObject.parse(result);
                        mTvCode.setText(object.getString("code"));
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}