package com.example.azureapp.ui.entity;

/**
 * fileDesc
 * Created by wzk on 2021/7/13.
 * Email 1403235458@qq.com
 */

/**
 * 警报类
 */
public class Alert {
    //警报描述
    public String operationName;
    //警报时间戳
    public String timeStamp;

    /**
     * 警报构造方法
     * @param operationName 警报描述
     */
    public Alert(String operationName) {
        this.operationName = operationName;
    }

    /**
     * 警报构造方法
     * @param operationName 警报描述
     * @param timeStamp 时间戳
     */
    public Alert(String operationName, String timeStamp) {
        this.operationName = operationName;
        this.timeStamp = timeStamp;
    }
}
