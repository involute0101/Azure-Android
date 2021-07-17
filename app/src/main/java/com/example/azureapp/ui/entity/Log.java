package com.example.azureapp.ui.entity;

import java.io.Serializable;

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

    public Log(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public Log(String content, String time, String type) {
        this.content = content;
        this.time = time;
        this.type = type;
    }

    public void changeTime(){
        String[] times = time.substring(0,16).split("T");
        time = times[0] + times[1];
    }
}
