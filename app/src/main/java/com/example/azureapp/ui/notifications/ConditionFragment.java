package com.example.azureapp.ui.notifications;

import android.os.Bundle;

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
import com.example.azureapp.R;
import com.example.azureapp.databinding.FragmentConditionBinding;
import com.example.azureapp.ui.Alert;
import com.example.azureapp.ui.Condition;
import com.example.azureapp.ui.Subscribe;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConditionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */
public class ConditionFragment extends Fragment {

    RecyclerView recyclerView;
    ConditionAdapter conditionAdapter;
    FragmentConditionBinding biding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConditionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConditionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConditionFragment newInstance(String param1, String param2) {
        ConditionFragment fragment = new ConditionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        biding = FragmentConditionBinding.inflate(inflater, container, false);
        View root = biding.getRoot();
        // Inflate the layout for this fragment
        return root;
    }

    /**
     * 实例化recycleView以及数据绑定adapter，
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("noti", "onActivityCreated: condition");
        recyclerView = biding.conditionRecycleView;
        conditionAdapter = new ConditionAdapter();
        conditionAdapter.conditions.clear();
        conditionAdapter.conditions.add(new Condition("test"));
        conditionAdapter.conditions.add(new Condition("test"));
        conditionAdapter.conditions.add(new Condition("test"));
        //getConditions();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(conditionAdapter);
    }

    /**
     * 从服务器端获取所有服务运行状况
     */
    private void getConditions() {
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
                        conditionAdapter.conditions.clear();
                        for(int i=0;i<jsonArray.size();i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Condition condition = new Condition(object.getString("condition"));
                            Log.d("subscribe", condition.condition);
                            //获取到数据库的描述信息
                            conditionAdapter.conditions.add(condition);
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

        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}