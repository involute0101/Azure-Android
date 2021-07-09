package com.example.azureapp.ui.virtualmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachine;

import java.util.List;

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