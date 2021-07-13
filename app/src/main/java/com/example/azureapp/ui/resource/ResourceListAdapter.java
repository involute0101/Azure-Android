package com.example.azureapp.ui.resource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-13 19:40
 **/
public class ResourceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Resource> resourceList = new ArrayList<>();

    public ResourceListAdapter(Context context, List<Resource> resourceList){
        mContext = context;
        this.resourceList = resourceList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new ResourceListAdapter.LinearViewHolder_Head(LayoutInflater.from(mContext).inflate(R.layout.resources_head,parent,false));
        }
        else{
            return new ResourceListAdapter.LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.resources_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(position!=0 && position < resourceList.size()){
            Resource resource = resourceList.get(position);
            if(resource.type.equals("虚拟机"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_virtualmachine);
            else if(resource.type.equals("资源组"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_resource_group);
            else if(resource.type.equals("SQL数据库"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_db);
            else if(resource.type.equals("磁盘"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_db);
            ((LinearViewHolder)holder).mResourceNameTv.setText(resource.name);
            ((LinearViewHolder)holder).mResourceTypeTv.setText(resource.type);
            if(resource.status.equals("stop"))
                ((LinearViewHolder)holder).mResourceStatusImg.setImageResource(R.drawable.icon_stop_hollow_128);
            else
                ((LinearViewHolder)holder).mResourceStatusImg.setImageResource(R.drawable.icon_running_hollow_128);
        }
    }

    @Override
    public int getItemCount() {
        return resourceList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class LinearViewHolder_Head extends RecyclerView.ViewHolder{

        private EditText mSearchEt;
        private TextView mCancelTv;

        public LinearViewHolder_Head( View itemView) {
            super(itemView);
            mSearchEt = itemView.findViewById(R.id.et_search_resources);
            mCancelTv = itemView.findViewById(R.id.tv_search_cancel);
            mSearchEt.setOnFocusChangeListener((v, hasFocus) -> {
                if(mSearchEt.hasFocus()){
                    mCancelTv.setText(R.string.resources_search_cancel);
                }else{
                    mCancelTv.setText("");
                }
            });
            mCancelTv.setOnClickListener(v -> {
                mCancelTv.setText("");
                mSearchEt.clearFocus();
            });
        }

    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView mResourceNameTv;
        private TextView mResourceTypeTv;
        private ImageView mResourceImg;
        private ImageView mResourceStatusImg;

        public LinearViewHolder( View itemView) {
            super(itemView);
            mResourceNameTv = itemView.findViewById(R.id.tv_resource_name);
            mResourceTypeTv = itemView.findViewById(R.id.tv_resource_type);
            mResourceImg = itemView.findViewById(R.id.img_resource);
            mResourceStatusImg = itemView.findViewById(R.id.img_resource_status);
        }
    }
}
