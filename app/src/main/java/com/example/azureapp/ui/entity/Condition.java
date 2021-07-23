package com.example.azureapp.ui.entity;

/**
 * fileDesc
 * Created by wzk on 2021/7/13.
 * Email 1403235458@qq.com
 */

/**
 * 服务运行状况类
 */
public class Condition {
    //服务运行状况描述
    public String condition;

    /**
     * 服务运行状况构造方法
     * @param condition
     */
    public Condition(String condition) {
        this.condition = condition;
    }
}
