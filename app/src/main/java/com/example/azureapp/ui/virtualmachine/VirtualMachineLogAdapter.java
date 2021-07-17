package com.example.azureapp.ui.virtualmachine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.entity.Log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * fileDesc
 * Created by wzk on 2021/7/14.
 * Email 1403235458@qq.com
 */
public class VirtualMachineLogAdapter extends RecyclerView.Adapter<VirtualMachineLogAdapter.VMLogViewHolder>{
    List<Log> logs = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public VMLogViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.log_head,parent,false);//???
            final VMLogViewHolder holder = new VMLogViewHolder(itemView);
            return holder;
        }
        else{
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.cell_log,parent,false);//???
            final VMLogViewHolder holder = new VMLogViewHolder(itemView);
            return holder;
        }
    }

    /**
     * 设置日志界面的控件信息以及图片
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull VMLogViewHolder holder, int position) {
        if(position!=0){
            Log log = logs.get(position-1);
            holder.logContentTextView.setText(log.content);
            holder.logTimeTextView.setText(log.time);
            if (log.type.equals("false") ){
                holder.logImage.setImageDrawable(ContextCompat.getDrawable(holder.logImage.getContext(), R.drawable.icon_alert_red));
            }
        }

    }


    @Override
    public int getItemCount() {
        return logs.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class VMLogViewHolder extends  RecyclerView.ViewHolder{
        TextView logContentTextView,logTimeTextView;
        ImageView logImage;
        public VMLogViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            logContentTextView = itemView.findViewById(R.id.log_content_textView);
            logTimeTextView = itemView.findViewById(R.id.log_time_textView);
            logImage = itemView.findViewById(R.id.log_imageView);
        }

    }
}
