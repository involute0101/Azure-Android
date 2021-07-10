package com.example.azureapp.ui.virtualmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-09 10:11
 **/
public class VirtualMachineDetailActivity extends AppCompatActivity {

    private RecyclerView mRvVMDetail;
    private ImageView mImgStart;
    private ImageView mImgDelete;
    private TextView mTvStart;
    private TextView mTvDelete;
    private boolean isStart;

    private TextView mTvVmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtualmachine_detail);
        mRvVMDetail = findViewById(R.id.rv_vm_details);
        mRvVMDetail.setLayoutManager(new LinearLayoutManager(VirtualMachineDetailActivity.this));
        mRvVMDetail.setAdapter(new VMDetailAdapter(VirtualMachineDetailActivity.this));
        mImgStart = findViewById(R.id.img_vm_detail_start);
        mImgDelete = findViewById(R.id.img_vm_detail_delete);
        mTvStart = findViewById(R.id.tv_vm_detail_start);
        mTvDelete = findViewById(R.id.tv_vm_detail_delete);
        isStart = true;
    }

    public void click_Event(View view){
        int id = view.getId();
        switch (id){
            case R.id.img_vm_detail_start:
                if(isStart){
                    mImgStart.setImageResource(R.drawable.icon_stop);
                    mTvStart.setText("停止");
                    mImgDelete.setImageResource(R.drawable.icon_restart);
                    mTvDelete.setText("重启");
                    isStart = false;
                    mRvVMDetail.setAdapter(new VMDetailAdapter_stop(VirtualMachineDetailActivity.this));
                }
                else{
                    mImgStart.setImageResource(R.drawable.icon_start);
                    mTvStart.setText("启动");
                    mImgDelete.setImageResource(R.drawable.icon_vmdetail_delete);
                    mTvDelete.setText("删除");
                    isStart = true;
                    mRvVMDetail.setAdapter(new VMDetailAdapter(VirtualMachineDetailActivity.this));
                }
                break;
            case R.id.img_vm_detail_delete:
                if(isStart){
                    mImgStart.setImageResource(R.drawable.icon_stop);
                    mTvStart.setText("停止");
                    mImgDelete.setImageResource(R.drawable.icon_restart);
                    mTvDelete.setText("重启");
                    isStart = false;
                    mRvVMDetail.setAdapter(new VMDetailAdapter_stop(VirtualMachineDetailActivity.this));
                }
                else{
                    mImgStart.setImageResource(R.drawable.icon_start);
                    mTvStart.setText("启动");
                    mImgDelete.setImageResource(R.drawable.icon_vmdetail_delete);
                    mTvDelete.setText("删除");
                    isStart = true;
                    mRvVMDetail.setAdapter(new VMDetailAdapter(VirtualMachineDetailActivity.this));
                }
                break;
        }
    }

//    public void start(){
//        RestTemplate restTemplate = new RestTemplate();
//        JSONObject response = restTemplate.getForObject("http://20.92.144.124:8080/Azure/allVM", JSONObject.class);
//        System.out.println(response);
//    }

    public void stop(){

    }
    public class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight));

        }
    }
}