package com.example.azureapp.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.entity.Condition;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/13.
 * Email 1403235458@qq.com
 */
public class ConditionAdapter extends RecyclerView.Adapter<ConditionAdapter.ConditionViewHolder> {

    List<Condition> conditions = new ArrayList<>();
    @NonNull
    @NotNull
    @Override
    public ConditionViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.cell_reource_condition,parent,false);

        final ConditionAdapter.ConditionViewHolder holder = new ConditionAdapter.ConditionViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ConditionViewHolder holder, int position) {

        Condition condition = conditions.get(position);

        holder.conditionTextView.setText(condition.condition);
    }

    @Override
    public int getItemCount() {
        return conditions.size();
    }

    static class ConditionViewHolder extends  RecyclerView.ViewHolder{
        TextView conditionTextView;
        public ConditionViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            conditionTextView = itemView.findViewById(R.id.log_content_textView);
        }
    }
}
