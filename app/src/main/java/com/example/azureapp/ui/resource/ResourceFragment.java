package com.example.azureapp.ui.resource;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.R;
import com.example.azureapp.databinding.FragmentResourceBinding;
import com.example.azureapp.databinding.SubscribeFragmentBinding;
import com.example.azureapp.ui.subscribe.SubscribeViewModel;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 * Update by 刘非凡 on 2021/7/13 21:24
 */
public class ResourceFragment extends Fragment {

    private RecyclerView mRvResources;
    private List<Resource> resourceList = new ArrayList<>();
    FragmentResourceBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResourceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getAllResources();
        ResourceViewModel mViewModel = new ViewModelProvider(this).get(ResourceViewModel.class);
        mRvResources = binding.rvResource;
        mRvResources.setLayoutManager(new LinearLayoutManager(requireActivity()));
        ResourceListAdapter adapter = new ResourceListAdapter(requireActivity(), resourceList);
        mRvResources.setAdapter(adapter);
    }

    public void setTestResource(){
        resourceList.add(new Resource("aaa","虚拟机"));
        resourceList.add(new Resource("bbb","数据库"));
        resourceList.add(new Resource("ccc","资源组"));
        resourceList.add(new Resource("ddd","资源组"));
        resourceList.add(new Resource("eee","虚拟机"));
        resourceList.add(new Resource("fff","数据库"));
    }

    private void getAllResources(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://20.89.169.250:8888/Resource/getAllResource?subscriptionId=fc4bf4a7-37a5-46c5-bd67-002062908beb";
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
                            String type = jsonObject.getString("type");
                            resourceList.add(new Resource(name,type));
                        }
                        System.out.println("资源组："+resourceList);
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