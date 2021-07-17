package com.example.azureapp.ui.database;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.R;
import com.example.azureapp.ui.entity.DataBase;
import com.example.azureapp.ui.entity.DataBaseDescription;
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
 * Use the {@link DataBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */
public class DataBaseFragment extends Fragment {

    RecyclerView recyclerView;
    DataBaseAdapter dbAdapter;
    private List<DataBase> dbs;
    FloatingActionButton addDBButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataBaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataBaseFragment newInstance(String param1, String param2) {
        DataBaseFragment fragment = new DataBaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 创建adapter实例
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dbAdapter = new DataBaseAdapter();
        Log.d("db", "DataBase");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_base, container, false);
    }

    /**
     * 实例化recycleView，获取服务器端数据库
     * 添加相应按钮点击事件，点击页面跳转
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = requireActivity().findViewById(R.id.db_recycle_view);
        dbAdapter.dbs.clear();
        //dbAdapter.dbs.add(new DataBase("test"));
        Log.d("db", "DataBase");
        getDataBase();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(dbAdapter);
        addDBButton = requireActivity().findViewById(R.id.addDBButton);
        addDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_DBFragment_to_DBAddFragment);
            }
        });
    }

    /**
     * 开启线程访问服务器获取用户所有的数据库
     */
    private void getDataBase() {
                Thread thread = new Thread(new Runnable() {
            JSONArray jsonArray;
            @Override
            public void run() {
                String url = "http://20.78.56.235:8080/DB/allDB";
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
                            dbAdapter.dbs.add(new DataBase(jsonObject.getString("name")));
                            dbAdapter.dbDescrptions.add(new DataBaseDescription(jsonObject.getString("resourceGroupName"),jsonObject.getString("sqlServerName"),
                                    jsonObject.getString("regionName"),jsonObject.getString("name"),
                                    jsonObject.getString("id"),jsonObject.getString("creationDate"),
                                    jsonObject.getString("status"),jsonObject.getString("maxSizeBytes"),jsonObject.getString("SqlServerId")));
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
}