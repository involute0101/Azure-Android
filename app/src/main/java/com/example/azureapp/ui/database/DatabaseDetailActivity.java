package com.example.azureapp.ui.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azureapp.R;
import com.example.azureapp.ui.entity.DataBaseDescription;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-13 9:00
 **/
public class DatabaseDetailActivity extends AppCompatActivity {

    //RecyclerView用于放置不同类型的item
    private RecyclerView mRvDBDetail;
    //一个数据库的详细数据
    public DataBaseDescription dbDetail;
    //点击进行删除数据库
    private LinearLayout llDelete;

    /**
     * 界面的启动方法
     * @param状态bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_detail);
        //获取传过来的数据库信息
        Intent intent = this.getIntent();
        dbDetail = (DataBaseDescription) intent.getExtras().getSerializable("DB");
        //初始化RecyclerView
        mRvDBDetail = findViewById(R.id.rv_db_details);
        mRvDBDetail.setLayoutManager(new LinearLayoutManager(DatabaseDetailActivity.this));
        mRvDBDetail.setAdapter(new DBDetailAdapter(DatabaseDetailActivity.this, dbDetail));
        llDelete = findViewById(R.id.ll_db_delete);
        //监听删除
        llDelete.setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(DatabaseDetailActivity.this);
            View toastView =inflater.inflate(R.layout.toast,null);
            TextView textView = toastView.findViewById(R.id.tv_tip);
            textView.setText(R.string.delete_db_going);
            Toast toast=new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(toastView);
            toast.setGravity(Gravity.CENTER,0, 0);
            toast.show();
            delete();
            System.out.println("DDDDDDDDDDDDDDDDeleteDB");
        });
    }

    /**
     * 根据传入的url对数据库进行相应的操作
     * @param url
     */
    private void dbOperation(String url){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("SqlServerId",dbDetail.sqlServerId);
            jsonObject.put("DBname",dbDetail.name);
        }catch (JSONException e) {
            e.printStackTrace();
        }
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
    }

    /**
     * 删除数据库
     */
    private void delete(){
        String url = "http://20.78.56.235:8080/DB/deleteDB";
        dbOperation(url);
    }
}