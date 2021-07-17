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
    private Context mContext;
    private DataBaseDescription db;

    public DBDetailAdapter(Context context, DataBaseDescription db){
        mContext = context;
        this.db = db;
    }


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

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     *
     */
    class LinearViewHolder_Head extends  RecyclerView.ViewHolder{

        private TextView tvDBName;
        private ImageView imgStatus;
        private TextView tvDBStatus;

        public  LinearViewHolder_Head(View itemView){
            super(itemView);
            tvDBName = itemView.findViewById(R.id.tv_db_name);
            imgStatus = itemView.findViewById(R.id.img_db_state);
            tvDBStatus = itemView.findViewById(R.id.tv_db_state);
        }
    }

    /**
     *
     */
    class LinearViewHolder_Blog extends  RecyclerView.ViewHolder{
        private TextView tvBlogCount;
        private TextView tvBlogMore;

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
     *
     */
    class LinearViewHolder_Source extends  RecyclerView.ViewHolder{
        private TextView tvSourceStatus;
        private ImageView imgSourceStatus;

        public  LinearViewHolder_Source(View itemView){
            super(itemView);
            tvSourceStatus = itemView.findViewById(R.id.tv_source_state);
            imgSourceStatus = itemView.findViewById(R.id.img_source_state);
        }
    }

    /**
     *
     */
    class LinearViewHolder_Attribute extends  RecyclerView.ViewHolder{
        private TextView tvSubscriptionID;
        private TextView tvSubscription;
        private TextView tvResourceGroup;
        private TextView tvLocation;
        private TextView tvDBName;
        private TextView tvServerName;
        private TextView tvMaxBytes;
        private TextView tvDBId;
        private TextView tvCreateTime;
        private ImageView imgLockSubscription;
        private ImageView imgLockId;
        private boolean subscriptionLock;
        private boolean idLock;
        private boolean isSubscriptionUpdated;

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
