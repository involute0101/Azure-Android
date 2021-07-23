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

    //activity的context
    private Context mContext;
    //资源组列表
    private List<ResourceGroup> resourceGroupList = new ArrayList<>();

    /**
     * 构造函数
     * @param context
     * @param resourceGroupList
     */
    public ResourceGroupAdapter(Context context, List<ResourceGroup> resourceGroupList){
        this.mContext = context;
        this.resourceGroupList = resourceGroupList;
    }

    /**
     * 页面创建的初始化
     * @param parent
     * @param viewType
     * @return 对应位置的item类型
     */
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

    /**
     * 对不同类型的item进行数据渲染
     * @param holder
     * @param position
     */
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

    /**
     * 设置item数量
     * @return item数量
     */
    @Override
    public int getItemCount() {
        return resourceGroupList.size()+1;
    }

    /**
     * 用整型数据表示不同类型的item
     * @param position
     * @return 对应位置的item类型
     */
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

    /**
     * 详细item类
     */
    class LinearViewHolder_Item extends  RecyclerView.ViewHolder {

        //显示资源名称的文本框
        private TextView mResourceNameTv;
        //显示资源位置的文本框
        private TextView mResourceLocationTv;
        //显示资源的图片框
        private ImageView mResourceImg;

        /**
         * 构造函数
         * @param itemView
         */
        public LinearViewHolder_Item(View itemView) {
            super(itemView);
            mResourceNameTv = itemView.findViewById(R.id.tv_resource_name);
            mResourceLocationTv = itemView.findViewById(R.id.tv_resource_type);
            mResourceImg = itemView.findViewById(R.id.img_resource);
        }
    }
}
