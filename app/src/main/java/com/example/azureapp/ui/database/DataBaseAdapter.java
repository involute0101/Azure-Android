package com.example.azureapp.ui.database;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.azureapp.R;
import com.example.azureapp.ui.DataBase;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */
public class DataBaseAdapter extends RecyclerView.Adapter<DataBaseAdapter.DataBaseViewHolder> {

    List<DataBase> dbs = new ArrayList<>();

    //传入后端得到的虚拟机列表
    public void setDbs (List<DataBase> dbs){
        this.dbs = dbs;
    }

    @NonNull
    @NotNull
    @Override
    public DataBaseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_db,parent,false);

        final DataBaseViewHolder holder = new DataBaseViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataBaseViewHolder holder, int position) {
        DataBase db = dbs.get(position);
        holder.itemView.setTag(R.id.db_for_view_holder,db);
        holder.dbNameTextView.setText(db.dataBaseName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(holder.itemView.getContext(), DatabaseDetailActivity.class );
                intent.putExtra("DB", db);
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(holder.itemView.getContext(), "跳转", Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return dbs.size();
    }

    static class DataBaseViewHolder extends  RecyclerView.ViewHolder{
        TextView dbNameTextView;
        public DataBaseViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            dbNameTextView = itemView.findViewById(R.id.db_name_textView);
        }
    }
}
