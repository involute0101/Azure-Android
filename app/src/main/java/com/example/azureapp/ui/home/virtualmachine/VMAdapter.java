package com.example.azureapp.ui.home.virtualmachine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return new VMViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VMViewHolder holder, int position) {
        VirtualMachine vm = vms.get(position);
        holder.vmNameTextView.setText(vm.name);
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
