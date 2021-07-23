package com.example.azureapp.ui.user;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.LoginActivity;
import com.example.azureapp.R;
import com.example.azureapp.StartActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
/**
 * fileDesc
 * Created by wzk on 2021/7/8.
 * Email 1403235458@qq.com
 */
public class UserFragment extends Fragment {
    //用户界面视图模型
    private UserViewModel mViewModel;
    //退出按钮控件
    Button quitButton;
    //用户邮箱、类型控件
    TextView userEmailTV, userTypeTV;
    //用户邮箱
    public String userEmail;
    //用户类型
    public String userType;

    /**
     * 界面实例
     * @return 用户界面实例
     */
    public static UserFragment newInstance() {
        return new UserFragment();
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
        return inflater.inflate(R.layout.user_fragment, container, false);
    }

    /**
     * activity创建同步
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        // TODO: Use the ViewModel
        quitButton = requireActivity().findViewById(R.id.quit_button);
        userEmailTV = requireActivity().findViewById(R.id.user_email_tv);
        userTypeTV = requireActivity().findViewById(R.id.user_type_tv);
        getUserInfo();
        userEmailTV.setText(userEmail);
        if (userType.equals("user")){userTypeTV.setText("用户");}
        //
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StartActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://20.89.169.250:8080/Azure/getAccountInfo";
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        JSONObject object = (JSONObject) JSONObject.parse(result);
                        JSONObject user = object.getJSONObject("user");
                        userEmail = user.getString("name");
                        userType = user.getString("type");
                        Log.d("user",userEmail);
                        //获取到数据库的描述信息

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