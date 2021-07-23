package com.example.azureapp.ui.subscribe;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.databinding.SubscribeFragmentBinding;
import com.example.azureapp.ui.entity.Subscribe;
import com.example.azureapp.ui.entity.VirtualMachine;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 */
public class SubscribeFragment extends Fragment {
    //订阅视图模型
    private SubscribeViewModel mViewModel;
    //试图控件
    RecyclerView recyclerView;
    //界面控制
    SubscribeAdapter subscribeAdapter;
    //订阅视图绑定
    SubscribeFragmentBinding binding;

    /**
     * 获取界面实例
     * @return 订阅界面实例
     */
    public static SubscribeFragment newInstance() {
        return new SubscribeFragment();
    }

    /**
     * 视图创建
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SubscribeFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    /**
     * 界面创建
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化recycleView和adapter
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SubscribeViewModel.class);
        // TODO: Use the ViewModel
        recyclerView = binding.subscribeRecycleView;
        subscribeAdapter = new SubscribeAdapter();
        /*subscribeAdapter.subscribes.clear();
        subscribeAdapter.subscribes.add(new Subscribe("免费订阅","ec269b4d-93af-43c5-9fd6-9a5185235344"));
        subscribeAdapter.subscribes.add(new Subscribe("免费订阅2","ec269b4d-93af-43c5-9fd6-9a5185235344"));*/
        getSubscribe();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(subscribeAdapter);
    }

    /**
     *从服务器端获取用户订阅
     */
    private void getSubscribe() {

        Thread thread = new Thread(new Runnable() {
            JSONArray jsonArray;
            @Override
            public void run() {
                String url = "http://20.89.169.250:8080/Azure/getSubscription";
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        jsonArray = (JSONArray) JSONArray.parse(result);
                        subscribeAdapter.subscribes.clear();
                        for(int i=0;i<jsonArray.size();i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Subscribe subscribe = new Subscribe(object.getString("subscriptionName"),object.getString("subscriptionId"));
                            Log.d("subscribe", subscribe.subscribeId);
                            //获取到数据库的描述信息
                            subscribeAdapter.subscribes.add(subscribe);
                        }
                        //System.out.println("結果："+result);
                        //vmAdapter.notifyDataSetChanged();

                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //添加线程
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}