package com.example.azureapp.ui.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * fileDesc
 * Created by wzk on 2021/7/14.
 * Email 1403235458@qq.com
 */

/**
 * 日志类
 */
public class Log implements Serializable {
    public String content;
    public String time;
    public String type;

    public Log(String content) {
        this.content = content;
    }

    public Log(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public Log(String content, String time, String type) {
        this.content = content;
        this.time = time;
        this.type = type;
    }

    public void changeTime() throws ParseException {
        String[] times = time.substring(0,16).split("T");
        time = times[0] +" "+times[1];

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatter.parse(time);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, +8); //太平洋时间转换为中国时间
        date=calendar.getTime(); //这个时间就是小时+8的结果

        time = formatter.format(date);
    }
}
