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
import com.example.azureapp.ui.virtualmachine.VMDetailAdapter;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/10.
 * Email 1403235458@qq.com
 */
public class SubscribeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Subscribe> subscribes = new ArrayList<>();
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.subscribe_head,parent,false);
            final SubscibeViewHolder holder = new SubscibeViewHolder(itemView);
            return holder;
        }
        else{
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.cell_subscribe,parent,false);
            final SubscibeViewHolder holder = new SubscibeViewHolder(itemView);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(position!=0 || position < subscribes.size()-1){
            Subscribe subscribe = subscribes.get(position);
            ((SubscibeViewHolder)holder).subscribeTypeTextView.setText(subscribe.subscribeType);
            ((SubscibeViewHolder)holder).subscribeIdTextView.setText(subscribe.subscribeId);
        }
    }

    @Override
    public int getItemCount() {
        return subscribes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class SubscibeViewHolder extends  RecyclerView.ViewHolder{
        TextView subscribeTypeTextView,subscribeIdTextView;
        public SubscibeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            subscribeTypeTextView = itemView.findViewById(R.id.subscribe_type_textView);
            subscribeIdTextView = itemView.findViewById(R.id.subscribe_id_textView);
        }
    }

    class SubscribeViewHolder_Head extends  RecyclerView.ViewHolder{

        public SubscribeViewHolder_Head(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
