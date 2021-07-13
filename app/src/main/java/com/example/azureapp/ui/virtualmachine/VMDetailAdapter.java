package com.example.azureapp.ui.virtualmachine;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachineDescription;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
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
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            return new LinearViewHolder_Source(LayoutInflater.from(mContext).inflate(R.layout.detail_source_running,parent,false));
        }
        if(viewType == 3){
            return new LinearViewHolder_Attribute(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_attribute,parent,false));
        }
        if(viewType == 4){
            return new LinearViewHolder_Chart(LayoutInflater.from(mContext).inflate(R.layout.virtualdetails_charts,parent,false));
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
            if(vm.status.equals("正在运行"))
                ((LinearViewHolder_Head)holder).imgStatus.setImageResource(R.drawable.icon_running_48);
            else
                ((LinearViewHolder_Head)holder).imgStatus.setImageResource(R.drawable.icon_stop_64);
        }
//        if(position == 1){
//            ((LinearViewHolder_Blog)holder).tvBlogCount.setText("10条信息");
//        }
        if(position == 2){
            ((LinearViewHolder_Source)holder).tvSourceStatus.setText(vm.status);
            if(vm.status.equals("正在运行")){
                ((LinearViewHolder_Source)holder).imgSourceStatus.setImageResource(R.drawable.icon_running_48);
            }
            else{
                ((LinearViewHolder_Source)holder).imgSourceStatus.setImageResource(R.drawable.icon_stop_64);
            }
        }
//        if(position == 3){
//            ((LinearViewHolder_Condition)holder).tvPowerState.setText("Power");
//            ((LinearViewHolder_Condition)holder).imgPowerState.setImageResource(R.drawable.icon_stop_64);
//            ((LinearViewHolder_Condition)holder).tvPreplanState.setText("Preplan");
//            ((LinearViewHolder_Condition)holder).imgPreplanState.setImageResource(R.drawable.icon_stop_64);
//        }
        if(position == 3){
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

        public  LinearViewHolder_Blog(View itemView){
            super(itemView);
            tvBlogCount = itemView.findViewById(R.id.tv_blog_count);
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
                    imgLockIp.setImageResource(R.drawable.icon_lock);
                    ipLock = false;
                }
                else{
                    tvPublicIp.setText("***************");
                    imgLockIp.setImageResource(R.drawable.icon_unlock);
                    ipLock = true;
                }
            });
        }

        private void getPublicIp(){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder url = new StringBuilder("http://20.92.144.124:8080/Azure/getIp?name=");
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

        private void getSubscription(){
            Thread thread = new Thread(new Runnable() {
                JSONArray jsonArray;
                @Override
                public void run() {
                    String url = "http://20.92.144.124:8080/Azure/getSubscription";
                    HttpClient client = HttpClients.createDefault();
                    HttpGet get = new HttpGet(url);
                    try{
                        HttpResponse response = client.execute(get);
                        int statusCode = response.getStatusLine().getStatusCode();
                        if (statusCode == 200) {
                            String result = EntityUtils.toString(response.getEntity());
                            jsonArray = (JSONArray) JSONArray.parse(result);
                            vm.subscriptionName = jsonArray.getString(0);
                            vm.subscriptionId = jsonArray.getString(1);
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

        private PieChart mPieChart;
        private List<String> dataList = new ArrayList<>();

        private LineChart mLineChart;

        public LinearViewHolder_Chart(View itemView) {
            super(itemView);
            mPieChart = itemView.findViewById(R.id.pie_chart);
            showPieChart(mPieChart, getPieChartData());
            mLineChart = itemView.findViewById(R.id.line_chart);
            setChartStyle(mLineChart);
            showLineChart(mLineChart, getLineChartData());
        }

        private List<PieEntry> getPieChartData() {
            //给刚才定义的dataList添加数据的方式，注意一下传递的参数是字符串哦
            dataList.add("xxx");
            dataList.add("yyy");
            dataList.add("zzz");

            List<PieEntry> mPie = new ArrayList<>();
            for (String data : dataList) {
                //下面这个PieEntry第一个参数主要要传递float类型的参数，表示百分比的
                PieEntry pieEntry = new PieEntry(15F, data);
                mPie.add(pieEntry);
            }
            return mPie;
        }

        private void setChartStyle(LineChart lineChart){
           XAxis xAxis = lineChart.getXAxis();
           xAxis.setDrawGridLines(false);
           xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
           xAxis.setTextColor(Color.parseColor("#787272"));
           xAxis.setTextSize(8);
           lineChart.getAxisRight().setEnabled(false);
           YAxis yAxis = lineChart.getAxisLeft();
           yAxis.setDrawAxisLine(false);
           yAxis.setAxisMaximum(20f);
           yAxis.setGranularity(0.5f);
           yAxis.setTextColor(Color.parseColor("#787272"));
           yAxis.setTextSize(8);
           lineChart.getDescription().setEnabled(false);
            //lineChart.setBackgroundColor(Color.parseColor("#3a7bd5"));
        }
        private List<Entry> getLineChartData() {
            List<Entry> lineEntry = new ArrayList<>();
            lineEntry.add(new Entry(1, 2));
            lineEntry.add(new Entry(1.5f, 6f));
            lineEntry.add(new Entry(2, 4));
            lineEntry.add(new Entry(2.5f, 10));
            lineEntry.add(new Entry(3, 6));
            lineEntry.add(new Entry(3.5f, 3));
            lineEntry.add(new Entry(4, 2));
            return lineEntry;
        }

        private void showLineChart(LineChart lineChart, List<Entry> lineList) {
            LineDataSet dataset = new LineDataSet(lineList, "line1");
            dataset.setDrawCircleHole(false);
            dataset.setDrawCircles(false);
            dataset.setLineWidth(0);
            dataset.setDrawValues(false);//不显示数据
            dataset.setDrawFilled(true);//填充色
            dataset.setFillDrawable(mContext.getResources().getDrawable(R.drawable.chart_fill_sky));
            LineData data = new LineData(dataset);
            lineChart.setData(data);
        }

        private void showPieChart(PieChart pieChart, List<PieEntry> pieList) {

            PieDataSet dataSet = new PieDataSet(pieList, "hhhhhhhh");

            // 设置颜色list，让不同的块显示不同颜色
            ArrayList<Integer> colors = new ArrayList<Integer>();
//            int[] MATERIAL_COLORS = {
//                    Color.rgb(0, 0, 0)
//            };
//            for (int c : MATERIAL_COLORS) {
//                colors.add(c);
//            }
            for (int c : ColorTemplate.VORDIPLOM_COLORS) {
                colors.add(c);
            }
            dataSet.setColors(colors);
            PieData pieData = new PieData(dataSet);

            // 设置描述，我设置了不显示，因为不好看，你也可以试试让它显示，真的不好看
            Description description = new Description();
            description.setEnabled(true);
            pieChart.setDescription(description);
            //设置半透明圆环的半径, 0为透明
            pieChart.setTransparentCircleRadius(0f);

            //设置初始旋转角度
            pieChart.setRotationAngle(-15);

            //数据连接线距图形片内部边界的距离，为百分数
            dataSet.setValueLinePart1OffsetPercentage(80f);

            //设置连接线的颜色
            dataSet.setValueLineColor(Color.LTGRAY);
            // 连接线在饼状图外面
            dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

            // 设置饼块之间的间隔
            dataSet.setSliceSpace(1f);
            dataSet.setHighlightEnabled(true);
            // 显示图例
            Legend legend = pieChart.getLegend();
            legend.setEnabled(true);

            // 和四周相隔一段距离,显示数据
            pieChart.setExtraOffsets(26, 5, 26, 5);

            // 设置pieChart图表是否可以手动旋转
            pieChart.setRotationEnabled(true);
            // 设置piecahrt图表点击Item高亮是否可用
            pieChart.setHighlightPerTapEnabled(true);
            // 设置pieChart图表展示动画效果，动画运行1.4秒结束
            pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
            //设置pieChart是否只显示饼图上百分比不显示文字
            pieChart.setDrawEntryLabels(true);
            //是否绘制PieChart内部中心文本
            pieChart.setDrawCenterText(false);
            // 绘制内容value，设置字体颜色大小
            pieData.setDrawValues(true);
            pieData.setValueFormatter(new PercentFormatter());
            pieData.setValueTextSize(10f);
            pieData.setValueTextColor(Color.DKGRAY);

            pieChart.setData(pieData);
            // 更新 piechart 视图
            pieChart.postInvalidate();
        }
    }
}
