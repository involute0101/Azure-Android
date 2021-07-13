package com.example.azureapp.ui.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.azureapp.R;
import com.example.azureapp.ui.virtualmachine.VMDetailAdapter;
import com.example.azureapp.ui.virtualmachine.VirtualMachineDetailActivity;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-13 9:00
 **/
public class DatabaseDetailActivity extends AppCompatActivity {

    RecyclerView mRvDBDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_detail);
        mRvDBDetail = findViewById(R.id.rv_db_details);
        mRvDBDetail.setLayoutManager(new LinearLayoutManager(DatabaseDetailActivity.this));
        mRvDBDetail.setAdapter(new DBDetailAdapter(DatabaseDetailActivity.this));
    }
}