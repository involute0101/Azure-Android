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
import com.example.azureapp.ui.entity.DataBase;
import com.example.azureapp.ui.entity.DataBaseDescription;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */
public class DataBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //数据库列表
    List<DataBase> dbs = new ArrayList<>();
    //数据库描述信息列表
    List<DataBaseDescription> dbDescrptions = new ArrayList<>();

    /**
     * 传入后端得到的虚拟机列表
     * @param dbs
     */
    public void setDbs (List<DataBase> dbs){
        this.dbs = dbs;
    }

    /**
     *视图容器创建
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.subscribe_head,parent,false);
            final DataBaseViewHolder_Head holder = new DataBaseViewHolder_Head(itemView);
            return holder;
        }
        else{
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.cell_db,parent,false);
            final DataBaseViewHolder holder = new DataBaseViewHolder(itemView);
            return holder;
        }
    }

    /**
     * 设置每一个itemView中的控件以及响应事件
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull  RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            ((DataBaseViewHolder_Head)holder).mTvName.setText("SQL数据库");
        }
        else{
            DataBase db = dbs.get(position-1);
            DataBaseDescription dataBaseDescription= dbDescrptions.get(position-1);
            holder.itemView.setTag(R.id.db_for_view_holder,db);
            ((DataBaseViewHolder)holder).dbNameTextView.setText(db.dataBaseName);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DatabaseDetailActivity.class );

                //传入数据库细节
                intent.putExtra("DB", dataBaseDescription);
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(holder.itemView.getContext(), "跳转", Toast.LENGTH_SHORT).show();
            });
        }
    }

    /**
     *
     * @return 数据库列表数目+1
     */
    @Override
    public int getItemCount() {
        return dbs.size()+1;
    }

    /**
     * 获取单元目录位置
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 数据库视图绑定类
     */
    static class DataBaseViewHolder extends  RecyclerView.ViewHolder{
        TextView dbNameTextView;

        /**
         * 绑定控件中的textView
         * @param itemView
         */
        public DataBaseViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            dbNameTextView = itemView.findViewById(R.id.db_name_textView);
        }
    }
    /**
     * 数据库视图头绑定类
     */
    class DataBaseViewHolder_Head extends  RecyclerView.ViewHolder{
        private TextView mTvName;
        public DataBaseViewHolder_Head(@NonNull @NotNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
