package com.example.azureapp.ui.virtualmachine;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.azureapp.R;
import com.example.azureapp.ui.entity.VirtualMachine;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/9.
 * Email 1403235458@qq.com
 */
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VMAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VMAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //虚拟机密码正则匹配
    static String PW_PATTERN = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    //虚拟机订阅ID
    String subscribe_id;
    //虚拟机网络名
    String vent_name;
    //虚拟机名
    String vm_name;
    //用户名
    String username;
    //密码
    String password;
    //虚拟机大小
    String vm_size;
    //虚拟机资源组
    String resource_group;
    //虚拟机信息控件
    EditText vent_name_text,vm_name_text,username_text,password_text,vm_size_text,resource_group_text;
    //虚拟机添加按钮
    Button add_submit_button;
    //虚拟机spinner
    private Spinner spinner;
    //大小列表
    private List<String> sizeList;
    //大小适配
    private ArrayAdapter<String> sizeAdapter;

    public VMAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VMAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VMAddFragment newInstance(String param1, String param2) {
        VMAddFragment fragment = new VMAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 界面创建
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
        return inflater.inflate(R.layout.fragment_v_m_add, container, false);
    }

    /**
     * 设置虚拟机添加界面控件，只有均有数据才可以点击按钮
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity= requireActivity();
        add_submit_button = activity.findViewById(R.id.createVMButton);
        vent_name_text = activity.findViewById(R.id.et_vm_vn);
        vm_name_text = activity.findViewById(R.id.et_vm_name);
        username_text = activity.findViewById(R.id.et_vm_user);
        password_text = activity.findViewById(R.id.et_vm_psw);

        //vm_size_text = activity.findViewById(R.id.et_vm_size);
        resource_group_text = activity.findViewById(R.id.et_vm_sourcegroup);
        add_submit_button.setEnabled(false);
        spinner = activity.findViewById(R.id.sp_vm_size);

        sizeList = new ArrayList<>();
        sizeList.add("Standard_B1s");
        sizeList.add("Standard_B2s");
        sizeList.add("Standard_B1ls");
        sizeList.add("Standard_B1ms");
        sizeList.add("Standard_A0");
        //适配器
        sizeAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, sizeList);
        //设置样式
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(sizeAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vm_size = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /**
         * 文本控件观察器
         */
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                vent_name = vent_name_text.getText().toString().trim();
                vm_name = vm_name_text.getText().toString().trim();
                username = username_text.getText().toString().trim();
                password = password_text.getText().toString().trim();
                //vm_size = vm_size_text.getText().toString().trim();
                resource_group = resource_group_text.getText().toString().trim();

                add_submit_button.setEnabled(!vent_name.isEmpty()
                                            && !username.isEmpty() && !password.isEmpty()
                                            /*&& !vm_size.isEmpty()*/ && !resource_group.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        vent_name_text.addTextChangedListener(watcher);
        vm_name_text.addTextChangedListener(watcher);
        username_text.addTextChangedListener(watcher);
        password_text.addTextChangedListener(watcher);
        //vm_size_text.addTextChangedListener(watcher);
        resource_group_text.addTextChangedListener(watcher);
        //添加提交按钮函数
        add_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.matches(PW_PATTERN)){
                    password_text.setText("");
                }

                else {
                    VirtualMachine virtualMachine = new VirtualMachine(subscribe_id, vent_name, vm_name, username, password, vm_size, resource_group);
                    Toast.makeText(getContext(), "创建成功，过程需要2~3min，请稍等", Toast.LENGTH_LONG).show();
                    addPostVM(virtualMachine);


                    NavController navController = Navigation.findNavController(v);
                    navController.navigateUp();
                }
            }
        });
    }

    /**
     * 向服务器发送添加虚拟机的请求
     * @param virtualMachine
     */
    public void addPostVM(VirtualMachine virtualMachine){
        JSONObject jsonObject = new JSONObject();
        String url = new String("http://20.89.169.250:8080/Azure/createVm");
        try {
            jsonObject.put("subscription_id","fc4bf4a7-37a5-46c5-bd67-002062908beb");
            jsonObject.put("VNET_NAME",virtualMachine.vnetName);
            jsonObject.put("VM_NAME",virtualMachine.vmName);
            jsonObject.put("USERNAME",virtualMachine.username);
            jsonObject.put("PASSWORD",virtualMachine.password);
            jsonObject.put("VM_SIZE",virtualMachine.vmSize);
            jsonObject.put("RESOURCE_GROUP_NAME",virtualMachine.resGroupName);
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