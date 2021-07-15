package com.example.azureapp.ui.home.resourceGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.resource.Resource;
import com.example.azureapp.ui.virtualmachine.VMDetailAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-15 15:47
 **/
public class ResourceGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ResourceGroup> resourceGroupList = new ArrayList<>();

    public ResourceGroupAdapter(Context context, List<ResourceGroup> resourceGroupList){
        this.mContext = context;
        this.resourceGroupList = resourceGroupList;
    }
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType==0){
            return new LinearViewHolder_Head(LayoutInflater.from(mContext).inflate(R.layout.subscribe_head,parent,false));
        }
        else{
            return new LinearViewHolder_Item(LayoutInflater.from(mContext).inflate(R.layout.resources_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            ((LinearViewHolder_Head)holder).tvName.setText(R.string.resourceGroup);
        }
        else{
            ResourceGroup resourceGroup = resourceGroupList.get(position-1);
            ((LinearViewHolder_Item)holder).mResourceImg.setImageResource(R.drawable.icon_resource_group);
            ((LinearViewHolder_Item)holder).mResourceNameTv.setText(resourceGroup.name);
            ((LinearViewHolder_Item)holder).mResourceLocationTv.setText(resourceGroup.location);
        }
    }

    @Override
    public int getItemCount() {
        return resourceGroupList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class LinearViewHolder_Head extends  RecyclerView.ViewHolder {
        private TextView tvName;
        public LinearViewHolder_Head(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    class LinearViewHolder_Item extends  RecyclerView.ViewHolder {

        private TextView mResourceNameTv;
        private TextView mResourceLocationTv;
        private ImageView mResourceImg;

        public LinearViewHolder_Item(View itemView) {
            super(itemView);
            mResourceNameTv = itemView.findViewById(R.id.tv_resource_name);
            mResourceLocationTv = itemView.findViewById(R.id.tv_resource_type);
            mResourceImg = itemView.findViewById(R.id.img_resource);
        }
    }
}
