package com.example.azureapp.ui.subscribe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.entity.Subscribe;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/10.
 * Email 1403235458@qq.com
 */
public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.SubscibeViewHolder> {

    List<Subscribe> subscribes = new ArrayList<>();
    @NonNull
    @NotNull
    @Override
    public SubscibeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
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

    /**
     * 设置textview中数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull SubscibeViewHolder holder, int position) {
        if (position!=0){
            Subscribe subscribe = subscribes.get(position-1);
            holder.subscribeTypeTextView.setText(subscribe.subscribeType);
            holder.subscribeIdTextView.setText(subscribe.subscribeId);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "设置为默认订阅", Toast.LENGTH_LONG).show();
                setDefaultSubscribe(holder.subscribeIdTextView.getText().toString());
            }
        });


    }

    /**
     * 设置默认订阅
     * @param subscriptionId 订阅ID
     */
    private void setDefaultSubscribe(String subscriptionId) {
        JSONObject jsonObject = new JSONObject();
        String url = new String("http://20.89.169.250:8080/Azure/setSubscription");
        try {
            jsonObject.put("subscriptionId",subscriptionId);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpClient client = HttpClients.createDefault();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

                    try {
                        StringEntity stringEntity = new StringEntity(jsonObject.toString());
                        stringEntity.setContentType("CONTENT_TYPE_TEXT_JSON");
                        httpPost.setEntity(stringEntity);
                        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpPost);
                        HttpEntity httpEntity = response.getEntity();
                        String s = EntityUtils.toString(httpEntity, "UTF-8");
                        System.out.println(s);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();

                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return subscribes.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class SubscibeViewHolder extends  RecyclerView.ViewHolder{
        TextView subscribeTypeTextView,subscribeIdTextView;

        /**
         * 实例化itemview中的控件
         * @param itemView
         */
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
