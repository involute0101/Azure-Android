package com.example.azureapp.ui.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.example.azureapp.R;
import com.example.azureapp.ui.entity.DataBaseDescription;
import com.example.azureapp.ui.entity.VirtualMachineDescription;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-13 9:06
 **/
public class DBDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @NotNull
    //传入的activity
    private Context mContext;
    //数据库描述信息
    private DataBaseDescription db;

    /**
     * 构造函数
     * @param context
     * @param db
     */
    public DBDetailAdapter(Context context, DataBaseDescription db){
        mContext = context;
        this.db = db;
    }


    /**
     * 通过对不同item类型设置，完成构造自定义的view
     * @param parent
     * @param viewType
     * @return 返回recyclerview的容器
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new DBDetailAdapter.LinearViewHolder_Head(LayoutInflater.from(mContext).inflate(R.layout.db_head,parent,false));
        }
        if(viewType == 1){
            return new DBDetailAdapter.LinearViewHolder_Blog(LayoutInflater.from(mContext).inflate(R.layout.detail_blog,parent,false));
        }
        if(viewType == 2){
            return new DBDetailAdapter.LinearViewHolder_Source(LayoutInflater.from(mContext).inflate(R.layout.detail_source_running,parent,false));
        }
        if(viewType == 3){
            return new DBDetailAdapter.LinearViewHolder_Attribute(LayoutInflater.from(mContext).inflate(R.layout.db_attribute,parent,false));
        }
        else
            return null;
    }

    /**
     * 对每一个item进行特殊的数据渲染
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            ((LinearViewHolder_Head)holder).tvDBName.setText(db.name);
            ((LinearViewHolder_Head)holder).tvDBStatus.setText(db.status);
            if(db.status != null && db.status.equals("Online"))
                ((LinearViewHolder_Head)holder).imgStatus.setImageResource(R.drawable.icon_running_48);
            else
                ((LinearViewHolder_Head)holder).imgStatus.setImageResource(R.drawable.icon_stop_64);
        }
        if(position == 2){
            ((LinearViewHolder_Source)holder).tvSourceStatus.setText(db.status);
            if(db.status != null && db.status.equals("Online")){
                ((LinearViewHolder_Source)holder).imgSourceStatus.setImageResource(R.drawable.icon_running_48);
            }
            else{
                ((LinearViewHolder_Source)holder).imgSourceStatus.setImageResource(R.drawable.icon_stop_64);
            }
        }

        if(position == 3){
            ((LinearViewHolder_Attribute)holder).tvSubscription.setText(R.string.subscription_default_name);
            ((LinearViewHolder_Attribute)holder).tvSubscriptionID.setText(R.string.subscription_default_id);
            ((LinearViewHolder_Attribute)holder).tvResourceGroup.setText(db.resourceGroupName);
            ((LinearViewHolder_Attribute)holder).tvLocation.setText(db.regionName);
            ((LinearViewHolder_Attribute)holder).tvDBName.setText(db.name);
            ((LinearViewHolder_Attribute)holder).tvServerName.setText(db.sqlServerName);
            ((LinearViewHolder_Attribute)holder).tvMaxBytes.setText(db.maxBytes);
            ((LinearViewHolder_Attribute)holder).tvDBId.setText(R.string.subscription_default_id);
            ((LinearViewHolder_Attribute)holder).tvCreateTime.setText(db.creationDate);
        }

    }

    /**
     * 返回表格中项目数量
     * @return item数目
     */
    @Override
    public int getItemCount() {
        return 4;
    }

    /**
     * 对每一个item设置对应的类型
     * @param position
     * @return item的类型
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 头部item类
     */
    class LinearViewHolder_Head extends  RecyclerView.ViewHolder{

        //显示数据库名字的文本框
        private TextView tvDBName;
        //显示数据库运行状态的图像框
        private ImageView imgStatus;
        //显示数据库状态的文本框
        private TextView tvDBStatus;

        /**
         * 构造函数
         * @param itemView
         */
        public  LinearViewHolder_Head(View itemView){
            super(itemView);
            tvDBName = itemView.findViewById(R.id.tv_db_name);
            imgStatus = itemView.findViewById(R.id.img_db_state);
            tvDBStatus = itemView.findViewById(R.id.tv_db_state);
        }
    }

    /**
     *日志item类
     */
    class LinearViewHolder_Blog extends  RecyclerView.ViewHolder{
        //显示日志数量的文本框
        private TextView tvBlogCount;
        //显示更多日志的文本框
        private TextView tvBlogMore;

        /**
         * 构造函数
         * @param itemView
         */
        public  LinearViewHolder_Blog(View itemView){
            super(itemView);
            tvBlogCount = itemView.findViewById(R.id.tv_blog_count);
            tvBlogMore = itemView.findViewById(R.id.tv_blog_more);
            tvBlogMore.setOnClickListener(v -> {
                DataBaseLogFragment logFragment = new DataBaseLogFragment();

                DatabaseDetailActivity activity =(DatabaseDetailActivity)itemView.getContext();
                logFragment.resourceGroup = activity.dbDetail.resourceGroupName;
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.database_deatilfragment_container, logFragment, null)
                        .addToBackStack(null)
                        .commit();

            });
        }
    }

    /**
     *资源运行状况item类
     */
    class LinearViewHolder_Source extends  RecyclerView.ViewHolder{
        //显示资源状态的文本框
        private TextView tvSourceStatus;
        //显示资源状态的图像框
        private ImageView imgSourceStatus;

        /**
         * 构造函数
         * @param itemView
         */
        public  LinearViewHolder_Source(View itemView){
            super(itemView);
            tvSourceStatus = itemView.findViewById(R.id.tv_source_state);
            imgSourceStatus = itemView.findViewById(R.id.img_source_state);
        }
    }

    /**
     *属性item类
     */
    class LinearViewHolder_Attribute extends  RecyclerView.ViewHolder{
        //显示订阅id文本框
        private TextView tvSubscriptionID;
        //显示订阅名的文本框
        private TextView tvSubscription;
        //显示资源组的文本框
        private TextView tvResourceGroup;
        //显示位置的文本框
        private TextView tvLocation;
        //显示数据库名字的文本框
        private TextView tvDBName;
        //显示服务器名字的文本框
        private TextView tvServerName;
        //显示最大字节数的文本框
        private TextView tvMaxBytes;
        //显示数据库id的文本框
        private TextView tvDBId;
        //显示创建时间的文本框
        private TextView tvCreateTime;
        //显示订阅锁的图片框
        private ImageView imgLockSubscription;
        //显示id锁的图片框
        private ImageView imgLockId;
        //订阅是否可见
        private boolean subscriptionLock;
        //id是否可见
        private boolean idLock;
        //订阅是否已更新
        private boolean isSubscriptionUpdated;

        /**
         * 构造函数
         * @param itemView
         */
        public  LinearViewHolder_Attribute(View itemView){
            super(itemView);
            subscriptionLock = true;
            idLock = true;
            isSubscriptionUpdated = false;
            tvSubscription = itemView.findViewById(R.id.tv_subscription);
            tvSubscriptionID = itemView.findViewById(R.id.tv_subscription_id);
            tvResourceGroup = itemView.findViewById(R.id.tv_group_name);
            tvLocation = itemView.findViewById(R.id.tv_db_location);
            tvDBName = itemView.findViewById(R.id.tv_db_name);
            tvServerName = itemView.findViewById(R.id.tv_server_name);
            tvMaxBytes = itemView.findViewById(R.id.tv_max_bytes);
            tvDBId = itemView.findViewById(R.id.tv_db_id);
            tvCreateTime = itemView.findViewById(R.id.tv_db_create_time);
            imgLockSubscription = itemView.findViewById(R.id.img_db_subscription_lock);
            imgLockId = itemView.findViewById(R.id.img_db_id_lock);
            //订阅是否可见的点击监听
            imgLockSubscription.setOnClickListener(v -> {
                if(!isSubscriptionUpdated){
                    getSubscription();
                    isSubscriptionUpdated = true;
                }
                if(subscriptionLock){
                    tvSubscription.setText(db.subscriptionName);
                    tvSubscriptionID.setText(db.subscriptionId);
                    imgLockSubscription.setImageResource(R.drawable.icon_unlock);
                    subscriptionLock = false;
                }
                else{
                    tvSubscription.setText("****");
                    tvSubscriptionID.setText("************************");
                    imgLockSubscription.setImageResource(R.drawable.icon_lock);
                    subscriptionLock = true;
                }
            });
            //id是否可见的点击监听
            imgLockId.setOnClickListener(v -> {
                if(idLock){
                    tvDBId.setText(db.id);
                    imgLockId.setImageResource(R.drawable.icon_unlock);
                    idLock = false;
                }
                else{
                    tvDBId.setText(R.string.subscription_default_id);
                    imgLockId.setImageResource(R.drawable.icon_lock);
                    idLock = true;
                }
            });
        }

        /**
         * 从服务器端获取订阅
         */
        public void getSubscription(){
            Thread thread = new Thread(new Runnable() {
                JSONArray jsonArray;
                @Override
                public void run() {
                    String url = "http://20.89.169.250:8080/Azure/getSubscription";
                    HttpClient client = HttpClients.createDefault();
                    HttpGet get = new HttpGet(url);
                    try{
                        HttpResponse response = client.execute(get);
                        int statusCode = response.getStatusLine().getStatusCode();
                        if (statusCode == 200) {
                            String result = EntityUtils.toString(response.getEntity());
                            jsonArray = (JSONArray) JSONArray.parse(result);

                            VirtualMachineDescription vmDes = jsonArray.getObject(0,VirtualMachineDescription.class);
                            db.subscriptionId = vmDes.subscriptionId;
                            db.subscriptionName = vmDes.subscriptionName;
                        }
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            try{
                thread.start();
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
