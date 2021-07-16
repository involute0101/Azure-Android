package com.example.azureapp.ui.virtualmachine;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachine;
import com.example.azureapp.ui.VirtualMachineDescription;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/8.
 * Email 1403235458@qq.com
 */
public class VMAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<VirtualMachine> vms = new ArrayList<>();

    //传入后端得到的虚拟机列表
    public void setDetailVMs(List<VirtualMachine> vms){
        this.vms = vms;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.subscribe_head,parent,false);

            final VMViewHolder_Head holder = new VMViewHolder_Head(itemView);
            return holder;
        }
        else{
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.cell_vm,parent,false);

            final VMViewHolder holder = new VMViewHolder(itemView);
            return holder;
        }
    }

    /**
     * 虚拟机每个条目设置数据以及添加按钮点击跳转界面
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            ((VMViewHolder_Head)holder).mTvName.setText("虚拟机");
        }
        else{
            VirtualMachine vm = vms.get(position-1);
            holder.itemView.setTag(R.id.vm_for_view_holder,vm);
            ((VMViewHolder)holder).vmNameTextView.setText(vm.vmName);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(),VirtualMachineDetailActivity.class );
                System.out.println(vm.resGroupName+vm.vmName);
                VirtualMachineDescription detailVM = getDetailVirtualMachine(vm);
                //点击虚拟机单元，传递该虚拟机描述信息
                intent.putExtra("DetailVM", detailVM);

                Toast.makeText(holder.itemView.getContext(), "跳转", Toast.LENGTH_SHORT).show();
                holder.itemView.getContext().startActivity(intent);
            });
        }
    }

    /**
     * 从服务器端获取选中虚拟机的详情信息
     * @param vm
     * @return该虚拟机的一系列详情信息
     */
    private VirtualMachineDescription getDetailVirtualMachine(VirtualMachine vm){
        VirtualMachineDescription detailVM = new VirtualMachineDescription();
        Thread thread = new Thread(new Runnable() {
            JSONObject object;
            @Override
            public void run() {
                //通过资源组和虚拟机名称获得详情信息
                String url = "http://20.89.169.250:8080/Azure/VmShow?resourceGroup="+vm.resGroupName+"&name="+vm.vmName;
                HttpClient client = HttpClients.createDefault();
                HttpGet get = new HttpGet(url);
                try{
                    HttpResponse response = client.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        String result = EntityUtils.toString(response.getEntity());
                        object = (JSONObject) JSONObject.parse(result);
                        detailVM.resourceGroup =  object.getString("resourceGroup");
                        detailVM.os = object.getString("OS");
                        detailVM.name = object.getString("name");
                        detailVM.location = object.getString("location");
                        detailVM.vmSize = object.getString("vmSize");
                        detailVM.diskName = object.getString("OS_Disk_name");
                        Log.d("detail", detailVM.resourceGroup+"--"+ detailVM.location+"--"+detailVM.vmSize+"--"+detailVM.diskName);

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
        System.out.println(detailVM.toString());

        return detailVM;
    }

    @Override
    public int getItemCount() {
        return vms.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class VMViewHolder extends  RecyclerView.ViewHolder{
        TextView vmNameTextView;
         public VMViewHolder(@NonNull @NotNull View itemView) {
             super(itemView);
             vmNameTextView = itemView.findViewById(R.id.vm_name_textView);
         }
     }

     class VMViewHolder_Head extends  RecyclerView.ViewHolder{
         private TextView mTvName;
         public VMViewHolder_Head(@NonNull @NotNull View itemView) {
             super(itemView);
             mTvName = itemView.findViewById(R.id.tv_name);
         }
     }


}
