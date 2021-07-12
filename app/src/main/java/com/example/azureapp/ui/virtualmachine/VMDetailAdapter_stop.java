package com.example.azureapp.ui.virtualmachine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-09 10:33
 **/
public class VMDetailAdapter_stop extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @NotNull
    private Context mContext;

    public VMDetailAdapter_stop(Context context){
        this.mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new LinearViewHolder_Head(LayoutInflater.from(mContext).inflate(R.layout.vituraldetails_head,parent,false));
        }
        if(viewType == 1){
            return new LinearViewHolder_Blog(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_blog,parent,false));
        }
        if(viewType == 2){
            return new LinearViewHolder_Source(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_source_running,parent,false));
        }
        if(viewType == 3){
            return new LinearViewHolder_Condition(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_condition,parent,false));
        }
        if(viewType == 4){
            return new LinearViewHolder_Attribute(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_attribute,parent,false));
        }
        else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            ((LinearViewHolder_Head)holder).tvVmName.setText("back");
            ((LinearViewHolder_Head)holder).tvVmState.setText("未运行");
            ((LinearViewHolder_Head)holder).imgState.setImageResource(R.drawable.icon_stop_64);
        }
        if(position == 1){
            ((LinearViewHolder_Blog)holder).tvBlogCount.setText("2条信息");
        }
        if(position == 2){
            ((LinearViewHolder_Source)holder).tvSourceState.setText("未知");
            ((LinearViewHolder_Source)holder).imgSourceState.setImageResource(R.drawable.icon_unkown_64);
        }
        if(position == 3){
            ((LinearViewHolder_Condition)holder).tvPowerState.setText("关闭");
            ((LinearViewHolder_Condition)holder).imgPowerState.setImageResource(R.drawable.icon_stop_64);
            ((LinearViewHolder_Condition)holder).tvPreplanState.setText("失败");
            ((LinearViewHolder_Condition)holder).imgPreplanState.setImageResource(R.drawable.icon_stop_64);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    /**
     *
     */
    class LinearViewHolder_Head extends  RecyclerView.ViewHolder{

        private TextView tvVmName;
        private ImageView imgState;
        private TextView tvVmState;

        public  LinearViewHolder_Head(View itemView){
            super(itemView);
            tvVmName = itemView.findViewById(R.id.tv_vm_name);
            imgState = itemView.findViewById(R.id.img_vm_state);
            tvVmState = itemView.findViewById(R.id.tv_vm_state);
        }
    }

    /**
     *
     */
    class LinearViewHolder_Blog extends  RecyclerView.ViewHolder{
        private TextView tvBlogCount;

        public  LinearViewHolder_Blog(View itemView){
            super(itemView);
            tvBlogCount = itemView.findViewById(R.id.tv_blog_count);
        }
    }

    /**
     *
     */
    class LinearViewHolder_Source extends  RecyclerView.ViewHolder{
        private TextView tvSourceState;
        private ImageView imgSourceState;

        public  LinearViewHolder_Source(View itemView){
            super(itemView);
            tvSourceState = itemView.findViewById(R.id.tv_source_state);
            imgSourceState = itemView.findViewById(R.id.img_source_state);
        }
    }

    /**
     *
     */
    class LinearViewHolder_Condition extends  RecyclerView.ViewHolder{
        private TextView tvPowerState;
        private ImageView imgPowerState;
        private TextView tvPreplanState;
        private ImageView imgPreplanState;
        public  LinearViewHolder_Condition(View itemView){
            super(itemView);
            tvPowerState = itemView.findViewById(R.id.tv_condition_power);
            imgPowerState = itemView.findViewById(R.id.img_condition_power);
            tvPreplanState = itemView.findViewById(R.id.tv_condition_preplan);
            imgPreplanState = itemView.findViewById(R.id.img_condition_preplan);
        }
    }

    /**
     *
     */
    class LinearViewHolder_Attribute extends  RecyclerView.ViewHolder{
        public  LinearViewHolder_Attribute(View itemView){
            super(itemView);
        }
    }
}
