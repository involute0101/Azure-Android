package com.example.azureapp.ui.virtualmachine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.azureapp.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-09 10:33
 **/
public class VMDetailAdapter extends RecyclerView.Adapter<VMDetailAdapter.LinearViewHolder> {
    @NonNull
    @NotNull
    private Context mContext;

    public VMDetailAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public VMDetailAdapter.LinearViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.vituraldetails_head,parent,false));
        }
        if(viewType == 1){
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_blog,parent,false));
        }
        if(viewType == 2){
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_source_running,parent,false));
        }
        if(viewType == 3){
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_condition,parent,false));
        }
        if(viewType == 4){
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_attribute,parent,false));
        }
        else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VMDetailAdapter.LinearViewHolder holder, int position) {
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class LinearViewHolder extends  RecyclerView.ViewHolder{
        public  LinearViewHolder(View itemView){
            super(itemView);
        }
    }
}
