package com.example.azureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-08 19:54
 **/
public class VerifyActivity extends AppCompatActivity {

    private TextView codeTv;
    private EditText codeEt;
    private Button verifyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        codeTv = findViewById(R.id.tv_verify_code);
        codeEt = findViewById(R.id.et_verify);
        verifyBtn = findViewById(R.id.btn_verify);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
    }

    private void verify(){
        String vCode = codeTv.getText().toString();
        String uCode = codeEt.getText().toString();
        if(vCode.equals(uCode)){
            Intent intent = new Intent(VerifyActivity.this, LoginWebActivity.class);
            startActivity(intent);
        }
    }
}