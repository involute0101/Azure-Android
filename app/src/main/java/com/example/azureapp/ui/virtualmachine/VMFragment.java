package com.example.azureapp.ui.virtualmachine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.Float4;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.R;
import com.example.azureapp.ui.entity.VirtualMachine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * fileDesc
 * Created by wzk on 2021/7/8.
 * Email 1403235458@qq.com
 */
public class VMFragment extends Fragment {
    //虚拟机列表视图控件
    RecyclerView recyclerView;
    //虚拟机控制器
    VMAdapter vmAdapter;
    //虚拟机列表
    private List<VirtualMachine> vms;
    //虚拟机添加按钮
    FloatingActionButton addVMButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VMFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VMFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VMFragment newInstance(String param1, String param2) {
        VMFragment fragment = new VMFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 虚拟机界面创建
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //适配器实例化
        vmAdapter = new VMAdapter();
        Log.d("vm", "VirtualMachine");


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_v_m, container, false);
    }

    /**
     * 设置按钮点击事件，初始实例化显示控件以及数据控制adapter
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addVMButton = requireActivity().findViewById(R.id.addVMButton);

        addVMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_VMFragment_to_VMAddFragment);
            }
        });
        recyclerView = requireActivity().findViewById(R.id.vm_recycle_view);

        getVirtualMachine();


        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(vmAdapter);
        Log.d("allvm","vmall");
    }

    /**
     * 从服务器端获取所有的虚拟机列表
     */
    public void getVirtualMachine() {
        Thread thread = new Thread(new Runnable() {
            JSONArray jsonArray;
            @Override
            public void run() {
                String url = "http://20.89.169.250:8080/Azure/allVM";
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        jsonArray = (JSONArray) JSONArray.parse(result);
                        vmAdapter.vms.clear();
                        for(int i=0;i<jsonArray.size();i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            VirtualMachine virtualMachine = new VirtualMachine(object.getString("name"));
                            virtualMachine.resGroupName = object.get("resourceGroup").toString();
                            Log.d("vm", virtualMachine.vmName);
                            //获取到数据库的描述信息
                            vmAdapter.vms.add(virtualMachine);
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