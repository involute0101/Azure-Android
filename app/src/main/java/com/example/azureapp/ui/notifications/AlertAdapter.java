package com.example.azureapp.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.entity.Alert;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/13.
 * Email 1403235458@qq.com
 */
public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {
    //警报列表
    List<Alert> alerts = new ArrayList<>();

    /**
     * 视图容器
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @NotNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.cell_alert,parent,false);

        final AlertViewHolder holder = new AlertViewHolder(itemView);
        return holder;
    }

    /**
     * 绑定视图
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull AlertViewHolder holder, int position) {
        Alert alert = alerts.get(position);

        holder.alertTextView.setText(alert.operationName);
    }

    /**
     * 获取列表数目
     * @return 警报书数目
     */
    @Override
    public int getItemCount() {
        return alerts.size();
    }

    /**
     * 警报视图控制类
     */
    static class AlertViewHolder extends  RecyclerView.ViewHolder{
        TextView alertTextView;
        public AlertViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            alertTextView = itemView.findViewById(R.id.log_content_textView);
        }
    }
}
