package com.example.azureapp.ui.virtualmachine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.databinding.FragmentVirtualMachineLogBinding;
import com.example.azureapp.ui.entity.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VirtualMachineLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * fileDesc
 * Created by wzk on 2021/7/14.
 * Email 1403235458@qq.com
 */
public class VirtualMachineLogFragment extends Fragment {
    //视图列表控件
    RecyclerView recyclerView;
    //数据绑定
    FragmentVirtualMachineLogBinding binding;
    //虚拟机日志控制
    VirtualMachineLogAdapter logAdapter;
    //日志列表
    List<Log> logList = new ArrayList<>();
    //当前虚拟机资源组
    public String resourceGroup;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VirtualMachineLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VirtualMachineLogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VirtualMachineLogFragment newInstance(String param1, String param2) {
        VirtualMachineLogFragment fragment = new VirtualMachineLogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 创建界面
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    /**
     * 实例化日志的recycleView和adapter
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLogs(resourceGroup);
        recyclerView = binding.virtualmachineLogRecycleView;
        logAdapter = new VirtualMachineLogAdapter(logList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(logAdapter);
    }

    /**
     * 从服务器端取得虚拟机的日志
     */
    public void getLogs(String resourceGroup) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://20.89.169.250:8080/Log/LogInGroup?resourceGroup="+resourceGroup;
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        JSONArray jsonArray = (JSONArray) JSONArray.parse(result);
                        for(int index= 0; index<jsonArray.size();index++){
                            JSONObject jsonObject = jsonArray.getJSONObject(index);
                            Log log = new Log(jsonObject.getString("operationName")+" "+jsonObject.getString("status"),jsonObject.getString("submissionTimestamp"));
                            log.changeTime();
                            logList.add(log);
                        }
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        //添加线程
        try{
            thread.start();
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 视图创建
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVirtualMachineLogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}