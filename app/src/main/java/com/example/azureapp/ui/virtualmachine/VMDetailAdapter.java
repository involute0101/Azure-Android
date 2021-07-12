package com.example.azureapp.ui.virtualmachine;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.azureapp.R;
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

import org.jetbrains.annotations.NotNull;

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

    public VMDetailAdapter(Context context){
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
            ((LinearViewHolder_Head)holder).tvVmName.setText("back");
            ((LinearViewHolder_Head)holder).tvVmState.setText("正在运行");
            ((LinearViewHolder_Head)holder).imgState.setImageResource(R.drawable.icon_running_48);
        }
//        if(position == 1){
//            ((LinearViewHolder_Blog)holder).tvBlogCount.setText("10条信息");
//        }
//        if(position == 2){
//            ((LinearViewHolder_Source)holder).tvSourceState.setText("Source");
//            ((LinearViewHolder_Source)holder).imgSourceState.setImageResource(R.drawable.icon_stop_64);
//        }
//        if(position == 3){
//            ((LinearViewHolder_Condition)holder).tvPowerState.setText("Power");
//            ((LinearViewHolder_Condition)holder).imgPowerState.setImageResource(R.drawable.icon_stop_64);
//            ((LinearViewHolder_Condition)holder).tvPreplanState.setText("Preplan");
//            ((LinearViewHolder_Condition)holder).imgPreplanState.setImageResource(R.drawable.icon_stop_64);
//        }
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
            tvBlogCount = itemView.findViewById(R.id.tv_vm_blog_count);
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
            tvSourceState = itemView.findViewById(R.id.tv_vm_source_state);
            imgSourceState = itemView.findViewById(R.id.img_vm_source_state);
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
