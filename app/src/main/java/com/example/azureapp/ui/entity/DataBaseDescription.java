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
    public String resourceGroupName;
    public String sqlServerName;
    public String regionName;
    public String name;
    public String id;
    public String creationDate;
    public String status;
    public String maxBytes;
    public String subscriptionName;
    public String subscriptionId;
    public String sqlServerId;

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
