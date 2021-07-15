package com.example.azureapp.ui;

import java.io.Serializable;

/**
 * fileDesc
 * Created by wzk on 2021/7/15.
 * Email 1403235458@qq.com
 */
public class DataBaseDescription implements Serializable {
    public String resourceGroupName;
    public String sqlServerName;
    public String regionName;
    public String name;
    public String id;
    public String creationDate;
    public String status;

    public DataBaseDescription(String resourceGroupName, String sqlServerName, String regionName, String name, String id, String creationDate, String status) {
        this.resourceGroupName = resourceGroupName;
        this.sqlServerName = sqlServerName;
        this.regionName = regionName;
        this.name = name;
        this.id = id;
        this.creationDate = creationDate;
        this.status = status;
    }
}
