package com.example.azureapp.ui.database;

import android.content.Intent;
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
import com.example.azureapp.databinding.FragmentDataBaseLogBinding;
import com.example.azureapp.ui.entity.DataBase;
import com.example.azureapp.ui.entity.DataBaseDescription;
import com.example.azureapp.ui.entity.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataBaseLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataBaseLogFragment extends Fragment {
    RecyclerView recyclerView;
    FragmentDataBaseLogBinding binding;
    DataBaseLogAdapter logAdapter;

    public String resourceGroup;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataBaseLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataBaseLogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataBaseLogFragment newInstance(String param1, String param2) {
        DataBaseLogFragment fragment = new DataBaseLogFragment();
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
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = binding.databaseLogRecycleView;
        logAdapter = new DataBaseLogAdapter();
        logAdapter.logs.clear();
        getDataBaseLog(resourceGroup);
        /*logAdapter.logs.add(new Log("test","today","true"));
        logAdapter.logs.add(new Log("test1","today","false"));*/
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(logAdapter);
    }

    private void getDataBaseLog(String resourceGroup) {
        Thread thread = new Thread(new Runnable() {
            JSONArray jsonArray;
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
                        jsonArray = (JSONArray) JSONArray.parse(result);
                        for(int i=0;i<jsonArray.size();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            logAdapter.logs.add(new Log(jsonObject.getString("operationName")+" "+jsonObject.getString("status"),jsonObject.getString("submissionTimestamp")));
                        }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDataBaseLogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}