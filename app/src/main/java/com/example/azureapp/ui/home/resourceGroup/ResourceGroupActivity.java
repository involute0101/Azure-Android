package com.example.azureapp.ui.home.resourceGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.R;
import com.example.azureapp.ui.resource.Resource;
import com.example.azureapp.ui.virtualmachine.VMDetailAdapter;
import com.example.azureapp.ui.virtualmachine.VirtualMachineDetailActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-15 15:26
 **/
public class ResourceGroupActivity extends AppCompatActivity {

    //容纳item的容器
    RecyclerView recyclerView;
    //资源组列表数组
    private List<ResourceGroup> resourceGroupList = new ArrayList<>();

    /**
     * 界面的初始化方法
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_group);
        getResourceGroups();
        recyclerView = findViewById(R.id.rv_resource_group);
        recyclerView.setLayoutManager(new LinearLayoutManager(ResourceGroupActivity.this));
        ResourceGroupAdapter adapter = new ResourceGroupAdapter(ResourceGroupActivity.this, resourceGroupList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 从服务器端获取所有资源组
     */
    private void getResourceGroups(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://20.89.169.250:8080/Resource/getAllGroup";
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        JSONArray jsonArray = (JSONArray) JSONArray.parse(result);
                        for(int index=0; index < jsonArray.size(); index++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(index);
                            String name = jsonObject.getString("name");
                            String location = jsonObject.getString("location");
                            resourceGroupList.add(new ResourceGroup(name,location));
                        }
                        System.out.println("资源组："+resourceGroupList);
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
}