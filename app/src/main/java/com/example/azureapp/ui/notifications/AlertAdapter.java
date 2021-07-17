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
    List<Alert> alerts = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.cell_alert,parent,false);

        final AlertViewHolder holder = new AlertViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AlertViewHolder holder, int position) {
        Alert alert = alerts.get(position);

        holder.alertTextView.setText(alert.operationName);
    }

    @Override
    public int getItemCount() {
        return alerts.size();
    }

    static class AlertViewHolder extends  RecyclerView.ViewHolder{
        TextView alertTextView;
        public AlertViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            alertTextView = itemView.findViewById(R.id.log_content_textView);
        }
    }
}
