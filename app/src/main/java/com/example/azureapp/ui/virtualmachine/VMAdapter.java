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
public class VMAdapter extends RecyclerView.Adapter<VMAdapter.VMViewHolder> {

    List<VirtualMachine> vms = new ArrayList<>();

    //传入后端得到的虚拟机列表
    public void setDetailVMs(List<VirtualMachine> vms){
        this.vms = vms;
    }

    @NonNull
    @NotNull
    @Override
    public VMViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_vm,parent,false);

        final VMViewHolder holder = new VMViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VMViewHolder holder, int position) {
        VirtualMachine vm = vms.get(position);
        holder.itemView.setTag(R.id.vm_for_view_holder,vm);

        holder.vmNameTextView.setText(vm.vmName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),VirtualMachineDetailActivity.class );
                System.out.println(vm.resGroupName+vm.vmName);
                VirtualMachineDescription detailVM = getDetailVirtualMachine(vm);
                //点击虚拟机单元，传递该虚拟机描述信息
                intent.putExtra("DetailVM", detailVM);

                Toast.makeText(holder.itemView.getContext(), "跳转", Toast.LENGTH_SHORT).show();
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

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
                        Log.d("detail", detailVM.resourceGroup+"--"+ detailVM.location+"--"+detailVM.vmSize);

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
        return vms.size();
    }

    static class VMViewHolder extends  RecyclerView.ViewHolder{
        TextView vmNameTextView;
         public VMViewHolder(@NonNull @NotNull View itemView) {
             super(itemView);
             vmNameTextView = itemView.findViewById(R.id.vm_name_textView);
         }
     }

}
