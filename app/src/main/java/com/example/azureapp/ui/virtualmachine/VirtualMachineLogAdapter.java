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
    //虚拟机日志列表
    List<Log> logs = new ArrayList<>();

    /**
     * 设置虚拟机日志
     * @param logs
     */
    public VirtualMachineLogAdapter(List<Log> logs){
        this.logs = logs;
    }

    /**
     * 日志视图创建
     * @param parent
     * @param viewType
     * @return
     */
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
        }

    }

    /**
     * 获取日志数量
     * @return
     */
    @Override
    public int getItemCount() {
        return logs.size()+1;
    }

    /**
     * 获取当前item位置
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 虚拟机日志视图控制
     */
    static class VMLogViewHolder extends  RecyclerView.ViewHolder{
        TextView logContentTextView,logTimeTextView;
        ImageView logImage;

        /**
         * 实例化控件
         * @param itemView
         */
        public VMLogViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            logContentTextView = itemView.findViewById(R.id.log_content_textView);
            logTimeTextView = itemView.findViewById(R.id.log_time_textView);
            logImage = itemView.findViewById(R.id.log_imageView);
        }

    }
}
