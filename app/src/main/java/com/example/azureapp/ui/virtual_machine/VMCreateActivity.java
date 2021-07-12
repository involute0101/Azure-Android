package com.example.azureapp.ui.virtual_machine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.azureapp.MainActivity;
import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachine;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class VMCreateActivity extends AppCompatActivity {

    String subscribe_id,vent_name,vm_name,username,password,vm_size,resource_group ;

    EditText vent_name_text,vm_name_text,username_text,password_text,vm_size_text,resource_group_text;
    Button add_submit_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vmcreate);
        Log.d("jump", "jump ");
        add_submit_button = findViewById(R.id.createVMButton1);
        vent_name_text = findViewById(R.id.et_vm_vn1);
        vm_name_text = findViewById(R.id.et_vm_name);
        username_text = findViewById(R.id.et_user);
        password_text = findViewById(R.id.et_psw);
        vm_size_text = findViewById(R.id.et_vm_size1);
        resource_group_text = findViewById(R.id.et_vm_sourcegroup1);
        add_submit_button.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                vent_name = vent_name_text.getText().toString().trim();
                vm_name = vm_name_text.getText().toString().trim();
                username = username_text.getText().toString().trim();
                password = password_text.getText().toString().trim();
                vm_size = vm_size_text.getText().toString().trim();
                resource_group = resource_group_text.getText().toString().trim();

                add_submit_button.setEnabled(!vent_name.isEmpty()
                        && !username.isEmpty() && !password.isEmpty()
                        && !vm_size.isEmpty() && !resource_group.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        vent_name_text.addTextChangedListener(watcher);
        vm_name_text.addTextChangedListener(watcher);
        username_text.addTextChangedListener(watcher);
        password_text.addTextChangedListener(watcher);
        vm_size_text.addTextChangedListener(watcher);
        resource_group_text.addTextChangedListener(watcher);

        add_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VirtualMachine virtualMachine = new VirtualMachine(subscribe_id,vent_name,vm_name,username,password,vm_size,resource_group);

                addPostVM(virtualMachine);
                Intent intent = new Intent(VMCreateActivity.this,MainActivity.class);

                startActivity(intent);

            }
        });
    }
    public void addPostVM(VirtualMachine virtualMachine){
        JSONObject jsonObject = new JSONObject();
        String url = new String("http://20.92.144.124:8080/Azure/createVm");
        try {
            jsonObject.put("subscription_id","ec269b4d-93af-43c5-9fd6-9a5185235344");
            jsonObject.put("VNET_NAME",virtualMachine.vnetName);
            jsonObject.put("VM_NAME",virtualMachine.vmName);
            jsonObject.put("USERNAME",virtualMachine.username);
            jsonObject.put("PASSWORD",virtualMachine.password);
            jsonObject.put("VM_SIZE",virtualMachine.vmSize);
            jsonObject.put("RESOURCE_GROUP_NAME",virtualMachine.resGroupName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClient client = HttpClients.createDefault();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

                    try {
                        StringEntity stringEntity = new StringEntity(jsonObject.toString());
                        stringEntity.setContentType("CONTENT_TYPE_TEXT_JSON");
                        httpPost.setEntity(stringEntity);
                        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpPost);
                        HttpEntity httpEntity = response.getEntity();
                        String s = EntityUtils.toString(httpEntity, "UTF-8");
                        System.out.println(s);
                        Toast.makeText(getApplicationContext(),"创建成功，过程需要2~3min，请稍等",Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();

                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}