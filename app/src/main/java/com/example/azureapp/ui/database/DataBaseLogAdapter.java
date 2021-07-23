package com.example.azureapp.ui.database;

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
 * Created by wzk on 2021/7/15.
 * Email 1403235458@qq.com
 */
public class DataBaseLogAdapter extends RecyclerView.Adapter<DataBaseLogAdapter.DBLogViewHolder>{
    //数据库日志
    List<Log> logs = new ArrayList<>();

    /**
     * 日志视图管理创建
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @NotNull
    @Override
    public DBLogViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_log,parent,false);//???
        final DBLogViewHolder holder = new DBLogViewHolder(itemView);
        return holder;
    }

    /**
     * 日志视图绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull DBLogViewHolder holder, int position) {
        Log log = logs.get(position);
        holder.logContentTextView.setText(log.content);
        holder.logTimeTextView.setText(log.time);
        /*if (log.type.equals("false") ){
            holder.logImage.setImageDrawable(ContextCompat.getDrawable(holder.logImage.getContext(), R.drawable.icon_alert_red));
        }*/
    }

    /**
     * 获取日志列表数码
     * @return
     */
    @Override
    public int getItemCount() {
        return logs.size();
    }

    /**
     * 数据库日志视图绑定类
     */
    static class DBLogViewHolder extends  RecyclerView.ViewHolder{
        TextView logContentTextView,logTimeTextView;
        ImageView logImage;
        public DBLogViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            logContentTextView = itemView.findViewById(R.id.log_content_textView);
            logTimeTextView = itemView.findViewById(R.id.log_time_textView);
            logImage = itemView.findViewById(R.id.log_imageView);
        }

    }
}

