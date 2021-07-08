package com.example.azureapp.ui.virtualmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachine;

import java.util.List;

public class VirtualMachineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    VMAdapter vmAdapter;
    private List<VirtualMachine> vms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_machine);
        recyclerView = findViewById(R.id.vm_recycle_view);
        vmAdapter = new VMAdapter();
        vmAdapter.vms.add(new VirtualMachine("test1"));
        vmAdapter.vms.add(new VirtualMachine("test2"));
        vmAdapter.vms.add(new VirtualMachine("test3"));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(vmAdapter);
    }

}