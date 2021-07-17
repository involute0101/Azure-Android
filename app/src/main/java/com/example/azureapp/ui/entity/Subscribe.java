package com.example.azureapp.ui.entity;

/**
 * fileDesc
 * Created by wzk on 2021/7/10.
 * Email 1403235458@qq.com
 */

/**
 * 订阅类
 */
public class Subscribe {
   public String subscribeType;
   public String subscribeId;

    public Subscribe(String subscribeType, String subscribeId) {
        this.subscribeType = subscribeType;
        this.subscribeId = subscribeId;
    }
}
