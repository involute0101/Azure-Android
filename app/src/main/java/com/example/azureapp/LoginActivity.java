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

import com.alibaba.fastjson.JSONArray;
import com.example.azureapp.ui.VirtualMachine;

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
 * @date 2021-07-08 09:21
 **/
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
        pswTip = "你的密码不正确。如果你不记得你的密码，请立即进行重置。";
        userEt = findViewById(R.id.et_user);
        pswEt = findViewById(R.id.et_psw);
        userTipTv = findViewById(R.id.tv_user_tip);
        pswTipTv = findViewById(R.id.tv_psw_tip);
        forgetTv = findViewById(R.id.tv_forget);
        //下划线
        forgetTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        forgetTv.getPaint().setAntiAlias(true);
        forgetTv.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri privacy_url = Uri.parse("https://account.live.com/acsr");
            intent.setData(privacy_url);
            startActivity(intent);
        });
        loginBtn = findViewById(R.id.btn_login_2);
        loginBtn.setOnClickListener(v -> logIn());
        getDetail();
    }

    private void getDetail(){
        Thread thread = new Thread(new Runnable() {
            JSONArray jsonArray;
            @Override
            public void run() {
                String url = "http://20.92.144.124:8080/Azure/allVM";
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
//                        jsonArray = (JSONArray) JSONArray.parse(result);
//                        for(int i=0;i<jsonArray.size();i++)
//                        {
//                            vmAdapter.vms.add(new VirtualMachine(jsonArray.getJSONObject(i).getString("name")));
//                        }
                        System.out.println("結果："+result);
                        //vmAdapter.notifyDataSetChanged();

                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void logIn(){
        Boolean userValid = true, pswValid = true;
        String userName = userEt.getText().toString();
        String psw = pswEt.getText().toString();
        if(!userName.equals("1935245590@qq.com")){
            userValid = false;
        }
        if(!psw.equals("Hello123456789//")){
            pswValid = false;
        }
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
        if(userValid && pswValid){
            Intent intent = new Intent(LoginActivity.this, com.example.azureapp.MainActivity.class);
            startActivity(intent);
        }
        /**
         * 验证登录是否成功
         * 成功则跳转
         */
    }
}