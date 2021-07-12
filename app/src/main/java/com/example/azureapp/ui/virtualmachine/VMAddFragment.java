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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachine;
import com.example.azureapp.util.PayHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    String subscribe_id,vent_name,vm_name,username,password,vm_size,resource_group ;

    EditText vent_name_text,vm_name_text,username_text,password_text,vm_size_text,resource_group_text;
    Button add_submit_button;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_v_m_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity= requireActivity();
        add_submit_button = activity.findViewById(R.id.add_concretevm_button);
        vent_name_text = activity.findViewById(R.id.editTextTextVNetName);
        vm_name_text = activity.findViewById(R.id.editTextTextVirtualMachineName);
        username_text = activity.findViewById(R.id.editTextTextVirtualMachineUsername);
        password_text = activity.findViewById(R.id.editTextTextVirtualMachinePassword);
        vm_size_text = activity.findViewById(R.id.editTextTextVirtualMachineSize);
        resource_group_text = activity.findViewById(R.id.editTextTextSourceGroup);
        add_submit_button.setEnabled(false);

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
                vm_size = vm_size_text.getText().toString().trim();
                resource_group = resource_group_text.getText().toString().trim();

                add_submit_button.setEnabled(!vent_name.isEmpty()
                                            && !username.isEmpty() && !password.isEmpty()
                                            && !vm_size.isEmpty() && !resource_group.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        vent_name_text.addTextChangedListener(watcher);
        vm_name_text.addTextChangedListener(watcher);
        username_text.addTextChangedListener(watcher);
        password_text.addTextChangedListener(watcher);
        vm_size_text.addTextChangedListener(watcher);
        resource_group_text.addTextChangedListener(watcher);

        add_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VirtualMachine virtualMachine = new VirtualMachine(subscribe_id,vent_name,vm_name,username,password,vm_size,resource_group);
                //创建虚拟机，调用API
                //addPostVM(virtualMachine);
                /*VMFragment vmFragment = (VMFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.VMFragment);
                vmFragment.addVM(virtualMachine);
                NavController navController = Navigation.findNavController(v);
                navController.navigateUp();*/
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("subscription_id","ec269b4d-93af-43c5-9fd6-9a5185235344");
                    jsonObject.put("VNET_NAME",virtualMachine.vnetName);
                    jsonObject.put("VM_NAME",virtualMachine.vmName);
                    jsonObject.put("USERNAME",virtualMachine.username);
                    jsonObject.put("PASSWORD",virtualMachine.password);
                    jsonObject.put("VM_SIZE",virtualMachine.vmSize);
                    jsonObject.put("RESOURCE_GROUP_NAME",virtualMachine.resGroupName);
                    String url = new String("http://20.92.144.124:8080/Azure/createVm");
                    Toast.makeText(getContext(), "准备发送", Toast.LENGTH_SHORT).show();

                    PayHttpUtils httpUtils = new PayHttpUtils();
                    String result = httpUtils.post(url,jsonObject.toString());//json解析字符串网络请求
                    Toast.makeText(getContext(), "创建", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
    public void addPostVM(VirtualMachine virtualMachine){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("subscription_id","ec269b4d-93af-43c5-9fd6-9a5185235344");
            jsonObject.put("VNET_NAME",virtualMachine.vnetName);
            jsonObject.put("VM_NAME",virtualMachine.vmName);
            jsonObject.put("USERNAME",virtualMachine.username);
            jsonObject.put("PASSWORD",virtualMachine.password);
            jsonObject.put("VM_SIZE",virtualMachine.vmSize);
            jsonObject.put("RESOURCE_GROUP_NAME",virtualMachine.resGroupName);



            String url = new String("http://20.92.144.124:8080/Azure/createVm");

/*
            URL url = new URL("http://20.92.144.124:8080/Azure/createVm");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            // 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            DataOutputStream os = new DataOutputStream( conn.getOutputStream());
            String content = String.valueOf(jsonObject);
            os.writeBytes(content);
            os.flush();
            os.close();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                Toast.makeText(getContext(), "创建成功", Toast.LENGTH_SHORT).show();
                conn.disconnect();
            }*/
            PayHttpUtils httpUtils = new PayHttpUtils();
            String result = httpUtils.post(url,jsonObject.toString());//json解析字符串网络请求
            Toast.makeText(getContext(), "创建", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}