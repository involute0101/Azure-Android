package com.example.azureapp.ui.subscribe;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.Subscribe;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/10.
 * Email 1403235458@qq.com
 */
public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.SubscibeViewHolder> {
    List<Subscribe> subscribes = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public SubscibeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_subscribe,parent,false);
        final SubscibeViewHolder holder = new SubscibeViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SubscibeViewHolder holder, int position) {
        Subscribe subscribe = subscribes.get(position);
        holder.subscribeTypeTextView.setText(subscribe.subscribeType);
        holder.subscribeIdTextView.setText(subscribe.subscribeId);
    }

    @Override
    public int getItemCount() {
        return subscribes.size();
    }


    static class SubscibeViewHolder extends  RecyclerView.ViewHolder{
        TextView subscribeTypeTextView,subscribeIdTextView;
        public SubscibeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            subscribeTypeTextView = itemView.findViewById(R.id.subscribe_type_textView);
            subscribeIdTextView = itemView.findViewById(R.id.subscribe_id_textView);
        }
    }
}
