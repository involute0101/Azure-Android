package com.example.azureapp.ui.entity;

import java.io.Serializable;

/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */

/**
 * 数据库类
 */
public class DataBase implements Serializable {
    public String dataBaseUsername;
    public String dataBasePassword;
    public String dataBaseName;
    public String resourceGroup;

    public DataBase(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public DataBase(String dataBaseUsername, String dataBasePassword, String dataBaseName, String resourceGroup) {
        this.dataBaseUsername = dataBaseUsername;
        this.dataBasePassword = dataBasePassword;
        this.dataBaseName = dataBaseName;
        this.resourceGroup = resourceGroup;
    }
}
