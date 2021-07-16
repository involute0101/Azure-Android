package com.example.azureapp.ui;

/**
 * fileDesc
 * Created by wzk on 2021/7/13.
 * Email 1403235458@qq.com
 */

/**
 * 警报类
 */
public class Alert {
    public String operationName;
    public String timeStamp;

    public Alert(String operationName) {
        this.operationName = operationName;
    }

    public Alert(String operationName, String timeStamp) {
        this.operationName = operationName;
        this.timeStamp = timeStamp;
    }
}
