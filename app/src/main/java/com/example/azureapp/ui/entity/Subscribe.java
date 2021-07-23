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
    //订阅类型
   public String subscribeType;
   //订阅 ID
   public String subscribeId;

    /**
     * 订阅构造函数
     * @param subscribeType
     * @param subscribeId
     */
    public Subscribe(String subscribeType, String subscribeId) {
        this.subscribeType = subscribeType;
        this.subscribeId = subscribeId;
    }
}
