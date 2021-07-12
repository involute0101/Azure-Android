package com.example.azureapp.ui.virtual_machine;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachine;

import org.jetbrains.annotations.NotNull;

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
    public void setVms (List<VirtualMachine> vms){
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
                intent.putExtra("VM", vm);
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(holder.itemView.getContext(), "跳转", Toast.LENGTH_SHORT).show();
            }
        });
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
