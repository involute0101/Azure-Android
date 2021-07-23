package com.example.azureapp.ui.database;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.R;
import com.example.azureapp.ui.entity.DataBase;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataBaseAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */
public class DataBaseAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //attributes of database
    String db_username,db_password,db_name,resource_group;
    //编辑控件
    EditText db_username_text,db_password_text,db_name_text,resource_group_text;
    //添加提交按钮
    Button add_submit_button;
    //密码正则匹配
    static String PW_PATTERN = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    //用户名正则匹配
    static String USERNAME = "/^[0-9a-zA-Z]*$/g";

    /**
     * 数据库添加界面构造函数
     */
    public DataBaseAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataBaseAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataBaseAddFragment newInstance(String param1, String param2) {
        DataBaseAddFragment fragment = new DataBaseAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 数据库添加界面创建
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
     * 数据库创建界面视图创建
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_base_add, container, false);
    }

    /**
     * 绑定界面控件，设置textwatcher，使得所有输入框有数据才可以点击按钮
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity= requireActivity();
        add_submit_button = activity.findViewById(R.id.createDBButton);
        db_username_text = activity.findViewById(R.id.et_db_username);
        db_password_text = activity.findViewById(R.id.et_db_psw);
        db_name_text = activity.findViewById(R.id.et_db_name);
        resource_group_text = activity.findViewById(R.id.et_db_sourcegroup);
        add_submit_button.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                db_username = db_username_text.getText().toString().trim();
                db_password = db_password_text.getText().toString().trim();
                db_name = db_name_text.getText().toString().trim();
                resource_group = resource_group_text.getText().toString().trim();


                add_submit_button.setEnabled(!db_username.isEmpty()
                        && !db_password.isEmpty() && !db_name.isEmpty()
                        && !resource_group.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };
        db_username_text.addTextChangedListener(watcher);
        db_password_text.addTextChangedListener(watcher);
        db_name_text.addTextChangedListener(watcher);
        resource_group_text.addTextChangedListener(watcher);


        /**
         * 按钮点击函数，提交数据库
         */
        add_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (!db_username.matches(USERNAME)){
                    db_username_text.setText("");
                }
                else return;*/

                if (!db_password.matches(PW_PATTERN)){
                    db_password_text.setText("");
                }

                else {
                    DataBase dataBase = new DataBase(db_username, db_password, db_name, resource_group);

                    Toast.makeText(getContext(), "创建成功，过程需要2~3min，请稍等", Toast.LENGTH_LONG).show();
                    addPostDB(dataBase);
                    NavController navController = Navigation.findNavController(v);
                    navController.navigateUp();
                }
            }


        });

    }

    /**
     * 将数据库信息提交到服务器处理，添加数据库
     * @param dataBase
     */
    public void addPostDB(DataBase dataBase) {
        JSONObject jsonObject = new JSONObject();
        String url = new String("http://20.78.56.235:8080/DB/createDB");
        try {
            jsonObject.put("userName",dataBase.dataBaseUsername);
            jsonObject.put("password",dataBase.dataBasePassword);
            jsonObject.put("DBname",dataBase.dataBaseName);
            jsonObject.put("resourceGroupName",dataBase.resourceGroup);

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}