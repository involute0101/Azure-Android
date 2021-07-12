package com.example.azureapp.ui.virtual_machine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachine;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-09 10:11
 **/
public class VirtualMachineDetailActivity extends AppCompatActivity {

    private RecyclerView mRvVMDetail;
    private ImageView mImgStart;
    private ImageView mImgDelete;
    private TextView mTvStart;
    private TextView mTvDelete;
    private boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtualmachine_detail);
        mRvVMDetail = findViewById(R.id.rv_vm_details);
        mRvVMDetail.setLayoutManager(new LinearLayoutManager(VirtualMachineDetailActivity.this));
        mRvVMDetail.setAdapter(new VMDetailAdapter(VirtualMachineDetailActivity.this));
        mImgStart = findViewById(R.id.img_vm_detail_start);
        mImgDelete = findViewById(R.id.img_vm_detail_delete);
        mTvStart = findViewById(R.id.tv_vm_detail_start);
        mTvDelete = findViewById(R.id.tv_vm_detail_delete);
        isStart = true;
        getVmInfo();
    }

    public void click_Event(View view){
        int id = view.getId();
        switch (id){
            case R.id.img_vm_detail_start:
                if(isStart){
                    mImgStart.setImageResource(R.drawable.icon_stop);
                    mTvStart.setText("停止");
                    mImgDelete.setImageResource(R.drawable.icon_restart);
                    mTvDelete.setText("重启");
                    isStart = false;
                }
                else{
                    mImgStart.setImageResource(R.drawable.icon_start);
                    mTvStart.setText("启动");
                    mImgDelete.setImageResource(R.drawable.icon_vmdetail_delete);
                    mTvDelete.setText("删除");
                    isStart = true;
                    mRvVMDetail.setAdapter(new VMDetailAdapter(VirtualMachineDetailActivity.this));
                }
                break;
            case R.id.img_vm_detail_delete:
                if(isStart){
                    mImgStart.setImageResource(R.drawable.icon_stop);
                    mTvStart.setText("停止");
                    mImgDelete.setImageResource(R.drawable.icon_restart);
                    mTvDelete.setText("重启");
                    isStart = false;
                }
                else{
                    mImgStart.setImageResource(R.drawable.icon_start);
                    mTvStart.setText("启动");
                    mImgDelete.setImageResource(R.drawable.icon_vmdetail_delete);
                    mTvDelete.setText("删除");
                    isStart = true;
                    mRvVMDetail.setAdapter(new VMDetailAdapter(VirtualMachineDetailActivity.this));
                }
                break;
        }
    }

    public void getVmInfo(){
        Thread thread = new Thread(new Runnable() {
            JSONArray jsonArray;
            @Override
            public void run() {
                String url = "http://20.92.144.124:8080/Azure/allVM";
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try {
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        jsonArray = (JSONArray) JSONArray.parse(result);
                        System.out.println("結果：" + jsonArray);

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

    private void vmOperation(String url){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("GROUP_NAME","NologinTest");
            jsonObject.put("OS_DISK_NAME","springboot_OsDisk_1_b9d829ffeeec4973a626dd87150ba0eb");
            jsonObject.put("VM_NAME","springboot");
        }catch (JSONException e) {
            e.printStackTrace();
        }
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
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void start(){
        String url = "http://20.92.144.124:8080/Azure/startVm";
        vmOperation(url);
    }

    private void stop(){
        String url = "http://20.92.144.124:8080/Azure/stopVm";
        vmOperation(url);
    }

    public class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}