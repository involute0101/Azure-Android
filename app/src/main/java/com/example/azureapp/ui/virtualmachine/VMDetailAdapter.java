package com.example.azureapp.ui.virtualmachine;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.example.azureapp.R;
import com.example.azureapp.ui.entity.VirtualMachineDescription;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-09 10:33
 **/
public class VMDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @NotNull
    private Context mContext;
    private VirtualMachineDescription vm;

    public VMDetailAdapter(Context context, VirtualMachineDescription vm){
        this.mContext = context;
        this.vm = vm;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new LinearViewHolder_Head(LayoutInflater.from(mContext).inflate(R.layout.vituraldetails_head,parent,false));
        }
        if(viewType == 1){
            return new LinearViewHolder_Blog(LayoutInflater.from(mContext).inflate(R.layout.detail_blog,parent,false));
        }
        if(viewType == 2){
            return new LinearViewHolder_Chart(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_charts,parent,false));
        }
        if(viewType == 3){
            return new LinearViewHolder_Source(LayoutInflater.from(mContext).inflate(R.layout.detail_source_running,parent,false));
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
            ((LinearViewHolder_Head)holder).tvVmName.setText(vm.name);
            ((LinearViewHolder_Head)holder).tvVmStatus.setText(vm.status);
            if(vm.status != null && vm.status.equals("正在运行")){
                ((LinearViewHolder_Head)holder).imgStatus.setImageResource(R.drawable.icon_running_48);
            }
            else if(vm.status.equals("已停止")){
                ((LinearViewHolder_Head)holder).imgStatus.setImageResource(R.drawable.icon_stop_64);
            }
            else{
                ((LinearViewHolder_Head)holder).imgStatus.setImageResource(R.drawable.icon_doing);
            }
        }
//        if(position == 1){
//            ((LinearViewHolder_Blog)holder).tvBlogCount.setText("10条信息");
//        }
        if(position == 3){
            ((LinearViewHolder_Source)holder).tvSourceStatus.setText(vm.status);
            if(vm.status != null && vm.status.equals("正在运行")){
                ((LinearViewHolder_Source)holder).imgSourceStatus.setImageResource(R.drawable.icon_running_48);
            }
            else if(vm.status.equals("已停止")){
                ((LinearViewHolder_Source)holder).imgSourceStatus.setImageResource(R.drawable.icon_stop_64);
            }
            else{
                ((LinearViewHolder_Source)holder).imgSourceStatus.setImageResource(R.drawable.icon_doing);
            }
        }
        if(position == 4){
            ((LinearViewHolder_Attribute)holder).tvResourceGroup.setText(vm.resourceGroup);
            ((LinearViewHolder_Attribute)holder).tvLocation.setText(vm.location);
            ((LinearViewHolder_Attribute)holder).tvVm.setText(vm.name);
            ((LinearViewHolder_Attribute)holder).tvOs.setText(vm.os);
            ((LinearViewHolder_Attribute)holder).tvSize.setText(vm.vmSize);
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
    class LinearViewHolder_Head extends  RecyclerView.ViewHolder {

        private TextView tvVmName;
        private ImageView imgStatus;
        private TextView tvVmStatus;

        public LinearViewHolder_Head(View itemView) {
            super(itemView);
            tvVmName = itemView.findViewById(R.id.tv_vm_name);
            imgStatus = itemView.findViewById(R.id.img_vm_state);
            tvVmStatus = itemView.findViewById(R.id.tv_vm_state);
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
            tvBlogMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VirtualMachineLogFragment logFragment = new VirtualMachineLogFragment();
                    VirtualMachineDetailActivity activity =(VirtualMachineDetailActivity)itemView.getContext();
                    logFragment.resourceGroup = vm.resourceGroup;
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.virtualmachine_deatilfragment_container, logFragment, null)
                            .addToBackStack(null)
                            .commit();
                }
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
        private TextView tvResourceGroup;
        private TextView tvLocation;
        private TextView tvVm;
        private TextView tvOs;
        private TextView tvSize;
        private TextView tvPublicIp;
        private TextView tvSubscriptionID;
        private TextView tvSubscription;
        private ImageView imgLockSubscription;
        private ImageView imgLockIp;
        private boolean subscriptionLock;
        private boolean ipLock;
        private boolean isSubscriptionUpdated;
        private boolean isIpUpdated;

        public  LinearViewHolder_Attribute(View itemView){
            super(itemView);
            subscriptionLock = true;
            ipLock = true;
            isSubscriptionUpdated = false;
            isIpUpdated = false;
            tvResourceGroup = itemView.findViewById(R.id.tv_resource_group);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvVm = itemView.findViewById(R.id.tv_computer_name);
            tvOs = itemView.findViewById(R.id.tv_os);
            tvSize = itemView.findViewById(R.id.tv_size);
            tvPublicIp = itemView.findViewById(R.id.tv_public_ip);
            tvSubscription = itemView.findViewById(R.id.tv_subscription);
            tvSubscriptionID = itemView.findViewById(R.id.tv_subscription_id);
            imgLockSubscription = itemView.findViewById(R.id.img_vm_subscription_lock);
            imgLockIp = itemView.findViewById(R.id.img_vm_ip_lock);
            imgLockSubscription.setOnClickListener(v -> {
                if(!isSubscriptionUpdated){
                    getSubscription();
                    isSubscriptionUpdated = true;
                }
                if(subscriptionLock){
                    tvSubscription.setText(vm.subscriptionName);
                    tvSubscriptionID.setText(vm.subscriptionId);
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
            imgLockIp.setOnClickListener(v -> {
                if(!isIpUpdated){
                    getPublicIp();
                    isIpUpdated = true;
                }
                if(ipLock){
                    tvPublicIp.setText(vm.publicIP);
                    imgLockIp.setImageResource(R.drawable.icon_unlock);
                    ipLock = false;
                }
                else{
                    tvPublicIp.setText("***************");
                    imgLockIp.setImageResource(R.drawable.icon_lock);
                    ipLock = true;
                }
            });
        }

        private void getPublicIp(){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder url = new StringBuilder("http://20.89.169.250:8080/Azure/getIp?name=");
                    url.append(vm.name);
                    HttpClient client = HttpClients.createDefault();
                    HttpGet get = new HttpGet(url.toString());
                    try {
                        HttpResponse response = client.execute(get);
                        int statusCode = response.getStatusLine().getStatusCode();
                        if (statusCode == 200) {
                            String result = EntityUtils.toString(response.getEntity());
                            vm.publicIP = result;
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
                            vm.subscriptionId = vmDes.subscriptionId;
                            vm.subscriptionName = vmDes.subscriptionName;
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

    class LinearViewHolder_Chart extends  RecyclerView.ViewHolder {

        private List<String> timeList = new ArrayList<>();
        private List<Float> valueList = new ArrayList<>();

        private LineChart mLineChart;

        public LinearViewHolder_Chart(View itemView) {
            super(itemView);
            mLineChart = itemView.findViewById(R.id.line_chart);
            timeList.add("8小时以前");
            timeList.add("7小时以前");
            timeList.add("6小时以前");
            timeList.add("5小时以前");
            timeList.add("4小时以前");
            timeList.add("3小时以前");
            timeList.add("2小时以前");
            timeList.add("1小时以前");
            timeList.add("现在");
            valueList.add(0f);
            valueList.add(0f);
            valueList.add(0f);
            valueList.add(30.65f);
            valueList.add(0f);
            valueList.add(0f);
            valueList.add(65.23f);
            valueList.add(0f);
            setChartStyle(mLineChart);
            showLineChart(mLineChart, getLineChartData());
        }

        private void setChartStyle(LineChart lineChart){
            XAxis xAxis = lineChart.getXAxis();

            XAxisValueFormatter xAxisValueFormatter = new XAxisValueFormatter(timeList.toArray(new String[0]));
            xAxis.setValueFormatter(xAxisValueFormatter);

           xAxis.setDrawGridLines(false);
           xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
           xAxis.setTextColor(Color.parseColor("#787272"));
           xAxis.setTextSize(6);
           lineChart.getAxisRight().setEnabled(false);
           YAxis yAxis = lineChart.getAxisLeft();
           yAxis.setDrawAxisLine(false);
           yAxis.setAxisMaximum(100f);
           yAxis.setGranularity(0.5f);
           yAxis.setTextColor(Color.parseColor("#787272"));
           yAxis.setTextSize(8);
           lineChart.getDescription().setEnabled(false);
            //lineChart.setBackgroundColor(Color.parseColor("#3a7bd5"));
        }
        private List<Entry> getLineChartData() {
            List<Entry> lineEntry = new ArrayList<>();
            for(int index=0; index< valueList.size(); index++){
                lineEntry.add(new Entry(index, valueList.get(index)));
            }
            return lineEntry;
        }

        private void showLineChart(LineChart lineChart, List<Entry> lineList) {
            LineDataSet dataset = new LineDataSet(lineList, "CPU平均使用率");
            dataset.setDrawCircleHole(false);
            dataset.setDrawCircles(false);
            dataset.setLineWidth(0);
            dataset.setDrawValues(false);//不显示数据
            dataset.setDrawFilled(true);//填充色
            dataset.setFillDrawable(mContext.getResources().getDrawable(R.drawable.chart_fill_sky));
            dataset.setColor(Color.parseColor("#3a7bd5"));
            LineData data = new LineData(dataset);
            lineChart.setData(data);
        }

        public class XAxisValueFormatter implements IAxisValueFormatter {

            private final String[] mLabels;
            public XAxisValueFormatter(String[] labels) {
                mLabels = labels;
            }
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                try {
                    return mLabels[(int) value];
                } catch (Exception e) {
                    e.printStackTrace();
                    return mLabels[0];
                }
            }
        }
    }
}
