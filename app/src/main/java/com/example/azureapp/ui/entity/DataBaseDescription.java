package com.example.azureapp.ui.entity;

import java.io.Serializable;

/**
 * fileDesc
 * Created by wzk on 2021/7/15.
 * Email 1403235458@qq.com
 */

/**
 * 数据库详情信息类
 */
public class DataBaseDescription implements Serializable {
    //资源组名
    public String resourceGroupName;
    //数据库服务器名
    public String sqlServerName;
    //地区名
    public String regionName;
    //数据库名
    public String name;
    //数据库ID
    public String id;
    //数据库创建时间
    public String creationDate;
    //数据库状态
    public String status;
    //数据库最大字节数
    public String maxBytes;
    //数据库订阅名
    public String subscriptionName;
    //数据库订阅ID
    public String subscriptionId;
    //数据库服务器ID
    public String sqlServerId;

    /**
     * 数据库构造方法
     * @param resourceGroupName
     * @param sqlServerName
     * @param regionName
     * @param name
     * @param id
     * @param creationDate
     * @param status
     * @param maxBytes
     * @param sqlServerId
     */
    public DataBaseDescription(String resourceGroupName, String sqlServerName, String regionName, String name, String id, String creationDate, String status, String maxBytes, String sqlServerId) {
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;
        this.regionName = regionName;
        this.name = name;
        this.id = id;
        this.creationDate = creationDate;
        this.status = status;
        this.maxBytes = maxBytes;
        this.sqlServerId = sqlServerId;
    }
}
