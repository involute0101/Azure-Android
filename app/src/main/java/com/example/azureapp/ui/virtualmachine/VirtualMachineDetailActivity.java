package com.example.azureapp.ui.virtualmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.entity.VirtualMachineDescription;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-09 10:11
 **/
public class VirtualMachineDetailActivity extends AppCompatActivity {

    //容纳item的表格
    private RecyclerView mRvVMDetail;
    //显示开始的图标
    private ImageView mImgStart;
    //显示删除的图标
    private ImageView mImgDelete;
    //显示开始的文本框
    private TextView mTvStart;
    //显示删除的文本框
    private TextView mTvDelete;
    //资源是否以及启动
    private boolean isStart;
    //虚拟机详细信息
    private VirtualMachineDescription vmDetail;
    //虚拟机详细信息adapter
    private VMDetailAdapter adapter;

    /**
     * 界面初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtualmachine_detail);
        //获取虚拟机描述信息
        Intent intent = this.getIntent();
        vmDetail = (VirtualMachineDescription) intent.getExtras().getSerializable("DetailVM");
        //获取虚拟机状态
        getStatus();
        //初始化RecyclerView
        mRvVMDetail = findViewById(R.id.rv_vm_details);
        mRvVMDetail.setLayoutManager(new LinearLayoutManager(VirtualMachineDetailActivity.this));
        adapter = new VMDetailAdapter(VirtualMachineDetailActivity.this, vmDetail);
        mRvVMDetail.setAdapter(adapter);
        //初始化操作栏
        mImgStart = findViewById(R.id.img_vm_detail_start);
        mImgDelete = findViewById(R.id.img_vm_detail_delete);
        mTvStart = findViewById(R.id.tv_vm_detail_start);
        mTvDelete = findViewById(R.id.tv_vm_detail_delete);
        //是否已经启动
        if(vmDetail.status.equals("正在运行")){
            isStart = true;
            mImgStart.setImageResource(R.drawable.icon_stop);
            mTvStart.setText(R.string.stop);
            mImgDelete.setImageResource(R.drawable.icon_restart);
            mTvDelete.setText(R.string.restart);
        }
        else{
            isStart = false;
            mImgStart.setImageResource(R.drawable.icon_start);
            mTvStart.setText(R.string.start);
            mImgDelete.setImageResource(R.drawable.icon_vmdetail_delete);
            mTvDelete.setText(R.string.delete);
        }
    }

    /**
     * 资源操纵点击事件
     * @param view
     */
    public void click_Event(View view){
        if(isStart){
            mImgStart.setImageResource(R.drawable.icon_start);
            mTvStart.setText(R.string.start);
            mImgDelete.setImageResource(R.drawable.icon_vmdetail_delete);
            mTvDelete.setText(R.string.delete);
        }
        else{
            mImgStart.setImageResource(R.drawable.icon_stop);
            mTvStart.setText(R.string.stop);
            mImgDelete.setImageResource(R.drawable.icon_restart);
            mTvDelete.setText(R.string.restart);
        }
        int id = view.getId();
        //判断点击按钮类型
        switch (id){
            case R.id.img_vm_detail_start:
                if(isStart){
                    stop();
                    showToast(getString(R.string.stop_going));
                    System.out.println("SSSSSSSSSSSSSSSSStop");
                    isStart = false;
                }
                else{
                    start();
                    showToast(getString(R.string.start_going));
                    isStart = true;
                }
                break;
            case R.id.img_vm_detail_delete:
                if(isStart){
                    restart();
                    showToast(getString(R.string.restart_going));
                    isStart = false;
                }
                else{
                    delete();
                    showToast(getString(R.string.delete_going));
                    isStart = true;
                }
                break;
        }
        getStatus();
        adapter.notifyDataSetChanged();
    }

    /**
     * 获取资源的状态
     */
    private void getStatus(){
        String groupName = vmDetail.resourceGroup;
        String vmName = vmDetail.name;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://20.89.169.250:8080/Azure/getStatus?resourceGroup="+groupName+"&name="+vmName;
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        if(result.equals("VM stopped")){
                            vmDetail.status = "已停止";
                            Log.d("status", vmDetail.status);
                        }
                        else if(result.equals("VM starting")){
                            vmDetail.status = "正在启动";
                            Log.d("status", vmDetail.status);
                        }
                        else if(result.equals("VM stopping")){
                            vmDetail.status = "正在停止";
                            Log.d("status", vmDetail.status);
                        }
                        else if(result.equals("VM deallocating")){
                            vmDetail.status = "正在分配";
                            Log.d("status", vmDetail.status);
                        }
                        else if (result.equals("VM running")){
                            vmDetail.status = "正在运行";
                            Log.d("status", vmDetail.status);
                        }
                        else{
                            vmDetail.status = "取消分配";
                            Log.d("status", vmDetail.status);
                        }
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try{
            thread.start();
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据url对虚拟机进行操作
     * @param url
     */
    private void vmOperation(String url){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("GROUP_NAME",vmDetail.resourceGroup);
            jsonObject.put("OS_DISK_NAME",vmDetail.diskName);
            jsonObject.put("VM_NAME",vmDetail.name);
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

    /**
     * 开启虚拟机
     */
    private void start(){
        String url = "http://20.89.169.250:8080/Azure/startVm";
        vmOperation(url);
    }

    /**
     * 停止虚拟机
     */
    private void stop(){
        String url = "http://20.89.169.250:8080/Azure/stopVm";
        vmOperation(url);
    }

    /**
     * 删除虚拟机
     */
    private void delete(){
        String url = "http://20.89.169.250:8080/Azure/deleteVm";
        vmOperation(url);
    }

    /**
     * 重启虚拟机
     */
    private void restart(){
        start();
    }

    /**
     * 自定义对话框
     * @param tip
     */
    private void showToast(String tip){
        LayoutInflater inflater = LayoutInflater.from(VirtualMachineDetailActivity.this);
        View toastView =inflater.inflate(R.layout.toast,null);
        TextView textView = toastView.findViewById(R.id.tv_tip);
        textView.setText(tip);
        Toast toast=new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER,0, 0);
        toast.show();
    }

    /**
     * item之间的样式修饰
     */
    public class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}