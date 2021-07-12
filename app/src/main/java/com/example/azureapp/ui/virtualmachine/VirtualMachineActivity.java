package com.example.azureapp.ui.virtualmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import android.os.Bundle;

import com.example.azureapp.R;

public class VirtualMachineActivity extends AppCompatActivity {
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_machine);

    }
//添加虚拟机
/*
    private boolean addVM(VirtualMachine vm){
        //调用API
        vmAdapter.vms.add(new VirtualMachine("testADD"));
        vmAdapter.notifyDataSetChanged();
        return true;
    }
*/


}